package com.javabootcamp.shoppingflow.services;

import com.javabootcamp.shoppingflow.ApplicationContext;
import com.javabootcamp.shoppingflow.exceptions.OrderNotFoundException;
import com.javabootcamp.shoppingflow.exceptions.OrderPaymentInvalidException;
import com.javabootcamp.shoppingflow.exceptions.ProductNotFoundException;
import com.javabootcamp.shoppingflow.models.*;
import com.javabootcamp.shoppingflow.repositories.*;
import com.javabootcamp.shoppingflow.utils.DateTimeUtil;
import com.javabootcamp.shoppingflow.views.order.NewOrderResponse;
import com.javabootcamp.shoppingflow.views.order.OrderProductResponse;
import com.javabootcamp.shoppingflow.views.order.OrderRequest;
import com.javabootcamp.shoppingflow.views.order.OrderResponse;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@NoArgsConstructor
public class OrderService {

    public OrderService(BasketService basketService,
                        BasketRepository basketRepository,
                        PaymentRepository paymentRepository,
                        MerchantRepository merchantRepository,
                        ProductRepository productRepository,
                        OrderRepository orderRepository,
                        OrderPaymentRepository orderPaymentRepository,
                        OrderAddressRepository orderAddressRepository,
                        OrderProductRepository orderProductRepository,
                        ApplicationContext applicationContext) {
        this.basketService = basketService;
        this.basketRepository = basketRepository;
        this.paymentRepository = paymentRepository;
        this.merchantRepository = merchantRepository;
        this.productRepository = productRepository;
        this.orderRepository = orderRepository;
        this.orderPaymentRepository = orderPaymentRepository;
        this.orderAddressRepository = orderAddressRepository;
        this.orderProductRepository = orderProductRepository;
        this.applicationContext = applicationContext;
    }

    @Autowired
    private BasketService basketService;

    @Autowired
    private BasketRepository basketRepository;

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private MerchantRepository merchantRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderPaymentRepository orderPaymentRepository;

    @Autowired
    private OrderAddressRepository orderAddressRepository;

    @Autowired
    private OrderProductRepository orderProductRepository;

    @Autowired
    private ApplicationContext applicationContext;

    @Transactional
    public NewOrderResponse createNewOrder(OrderRequest newOrder) {
        var userBasket = basketService.getUserBasket();
        Payment payment = paymentRepository.findByPaymentMethodSlug(newOrder.getPaymentMethod())
                .orElseThrow(() -> new OrderPaymentInvalidException("Payment method is invalid"));

        var order = new Order();

        User currentUser = applicationContext.getCurrentUser();
        order.setPayer(currentUser);

        var orderPayment = new OrderPayment();
        orderPayment.setPaymentMethod(payment.getPaymentMethodSlug());
        order.setOrderPayment(orderPayment);

        OrderAddress orderAddress = new OrderAddress();
        orderAddress.setAddressNumber(currentUser.getAddress().getAddressNumber());
        orderAddress.setSubdistrict(currentUser.getAddress().getSubdistrict());
        orderAddress.setDistrict(currentUser.getAddress().getDistrict());
        orderAddress.setProvince(currentUser.getAddress().getProvince());
        orderAddress.setZipCode(currentUser.getAddress().getZipCode());
        orderAddress.setEmail(currentUser.getAddress().getEmail());
        orderAddress.setTel(currentUser.getAddress().getTel());
        order.setOrderAddress(orderAddress);

        var now = new Date();
        order.setOrderDateTime(now);

        final int EXPIRED_PAYMENT_IN_DAYS = 3;
        order.setPaymentExpiredDateTime(DateTimeUtil.addDay(new Date(), EXPIRED_PAYMENT_IN_DAYS));

        order.setTotalAmount(userBasket.getTotalAmount());

        var orderProducts = new ArrayList<OrderProduct>();
        for (var p : userBasket.getBasketItems()) {
            var orderProduct = new OrderProduct();
            var product = productRepository.findById(p.getProductId()).orElseThrow(
                    () -> new ProductNotFoundException("Product not found"));

            orderProduct.setProduct(product);
            orderProduct.setOrder(order);
            orderProduct.setAmount(product.getNetPrice());
            orderProduct.setQuantity(p.getQuantity());
            orderProducts.add(orderProduct);
        }
        order.setOrderProducts(orderProducts);

        order.setInvoiceNumber(UUID.randomUUID().toString());
        order.setNote(newOrder.getNote());

        Order savedOrder = orderRepository.save(order);
        orderPaymentRepository.save(order.getOrderPayment());
        orderAddressRepository.save(order.getOrderAddress());
        orderProductRepository.saveAll(order.getOrderProducts());
        basketService.cleanupUserBasket();

        return new NewOrderResponse(savedOrder.getId());
    }

    public OrderResponse getUserOrder(long orderId) {
        var orderView = new OrderResponse();
        var order = orderRepository.findById(orderId)
                .orElseThrow(() -> new OrderNotFoundException("Order not found"));

        orderView.setId(order.getId());
        orderView.setInvoiceNumber(order.getInvoiceNumber());
        orderView.setOrderDateTime(order.getOrderDateTime());
        orderView.setPaymentExpiredDateTime(order.getPaymentExpiredDateTime());
        orderView.setTotalAmount(order.getTotalAmount());
        orderView.setNote(order.getNote());

        var orderProductViews = new ArrayList<OrderProductResponse>();
        for (var p : order.getOrderProducts()) {
            var orderProductView = new OrderProductResponse(
                    p.getId(),
                    p.getProduct().getName(),
                    p.getAmount(),
                    p.getQuantity());
            orderProductViews.add(orderProductView);
        }
        orderView.setResult(orderProductViews);

        return orderView;
    }
}
