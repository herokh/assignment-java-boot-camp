package com.javabootcamp.shoppingflow.services;

import com.javabootcamp.shoppingflow.ApplicationContext;
import com.javabootcamp.shoppingflow.exceptions.OrderNotFoundException;
import com.javabootcamp.shoppingflow.exceptions.OrderPaymentInvalidException;
import com.javabootcamp.shoppingflow.exceptions.ProductNotFoundException;
import com.javabootcamp.shoppingflow.models.*;
import com.javabootcamp.shoppingflow.repositories.*;
import com.javabootcamp.shoppingflow.seeders.MerchantSeeder;
import com.javabootcamp.shoppingflow.utils.DateTimeUtil;
import com.javabootcamp.shoppingflow.views.order.OrderRequest;
import com.javabootcamp.shoppingflow.views.order.OrderResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class OrderService {

    @Autowired
    private BasketService basketService;

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
    private OrderProductRepository orderProductRepository;

    @Autowired
    private ApplicationContext applicationContext;

    @Transactional
    public void createNewOrder(OrderRequest newOrder) {
        Payment payment = paymentRepository.findByPaymentMethodSlug(newOrder.getPaymentMethod())
                .orElseThrow(() -> new OrderPaymentInvalidException("Payment method is invalid"));

        var order = new Order();
        var orderPayment = new OrderPayment();
        orderPayment.setPaymentMethod(payment.getPaymentMethodSlug());
        order.setOrderPayment(orderPayment);

        var now = new Date();
        order.setOrderDateTime(now);

        final int expiredPaymentInDays = 3;
        order.setPaymentExpiredDateTime(DateTimeUtil.addDay(new Date(), expiredPaymentInDays));

        User currentUser = applicationContext.getCurrentUser();
        order.setPayer(currentUser);

        var userBasket = basketService.getUserBasket();
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
        order.setNote("Test.");

        orderRepository.save(order);
        orderPaymentRepository.save(order.getOrderPayment());
        orderProductRepository.saveAll(order.getOrderProducts());
    }

    public OrderResponse getUserOrder(long orderId) {
        var orderResponse = new OrderResponse();
        var order = orderRepository.findById(orderId)
                .orElseThrow(() -> new OrderNotFoundException("Order not found"));

        return orderResponse;
    }
}
