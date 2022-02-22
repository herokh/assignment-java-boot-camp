package com.javabootcamp.shoppingflow.services;

import com.javabootcamp.shoppingflow.ApplicationContext;
import com.javabootcamp.shoppingflow.mocks.BasketDataMock;
import com.javabootcamp.shoppingflow.mocks.ProductDataMock;
import com.javabootcamp.shoppingflow.mocks.UserDataMock;
import com.javabootcamp.shoppingflow.models.*;
import com.javabootcamp.shoppingflow.repositories.*;
import com.javabootcamp.shoppingflow.views.basket.BasketResponse;
import com.javabootcamp.shoppingflow.views.order.NewOrderResponse;
import com.javabootcamp.shoppingflow.views.order.OrderRequest;
import com.javabootcamp.shoppingflow.views.order.OrderResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class OrderServiceTests {

    @Mock
    private BasketService basketServiceMock;

    @Mock
    private BasketRepository basketRepositoryMock;

    @Mock
    private PaymentRepository paymentRepositoryMock;

    @Mock
    private MerchantRepository merchantRepositoryMock;

    @Mock
    private ProductRepository productRepositoryMock;

    @Mock
    private OrderRepository orderRepositoryMock;

    @Mock
    private OrderPaymentRepository orderPaymentRepositoryMock;

    @Mock OrderAddressRepository orderAddressRepositoryMock;

    @Mock
    private OrderProductRepository orderProductRepositoryMock;

    @Mock
    private ApplicationContext applicationContextMock;

    private OrderService orderServiceTest;

    @BeforeEach
    void setup() {
        lenient().when(applicationContextMock.getCurrentUser()).thenReturn(UserDataMock.createUserMock());

        this.orderServiceTest = new OrderService(basketServiceMock,
                basketRepositoryMock,
                paymentRepositoryMock,
                merchantRepositoryMock,
                productRepositoryMock,
                orderRepositoryMock,
                orderPaymentRepositoryMock,
                orderAddressRepositoryMock,
                orderProductRepositoryMock,
                applicationContextMock);
    }

    @Test
    void createNewOrderShouldBeSuccess() {
        // Arrange
        when(basketServiceMock.getUserBasket()).thenReturn(createUserBasketResponseMock());
        when(paymentRepositoryMock.findByPaymentMethodSlug(Mockito.anyString())).thenReturn(createPaymentMock());
        when(productRepositoryMock.findById(Mockito.anyLong())).thenReturn(Optional.of(ProductDataMock.getProduct()));
        when(orderRepositoryMock.save(Mockito.any(Order.class))).thenReturn(createOrderMock().get());

        OrderRequest orderRequest = new OrderRequest();
        orderRequest.setPaymentMethod("test");

        // Act
        NewOrderResponse result = orderServiceTest.createNewOrder(orderRequest);

        // Assert
        assertNotNull(result);
        verify(orderRepositoryMock, times(1)).save(Mockito.any(Order.class));
        verify(orderPaymentRepositoryMock, times(1)).save(Mockito.any(OrderPayment.class));
        verify(orderProductRepositoryMock, times(1)).saveAll(Mockito.any(ArrayList.class));
        verify(basketServiceMock, times(1)).cleanupUserBasket();
    }

    @Test
    void getUserOrderShouldBeSuccess() {
        // Arrange
        when(orderRepositoryMock.findById(Mockito.anyLong())).thenReturn(createOrderMock());

        // Act
        OrderResponse result = orderServiceTest.getUserOrder(1);

        // Assert
        assertNotNull(result);
    }

    private BasketResponse createUserBasketResponseMock() {
        var basketResponse = BasketDataMock.createUserBasketResponseMock();
        return basketResponse;
    }

    private Optional<Payment> createPaymentMock() {
        Payment payment = new Payment();
        payment.setId(1L);
        payment.setPaymentMethodName("test");
        payment.setPaymentMethodSlug("test");
        payment.setEnabled(true);
        return Optional.of(payment);
    }

    private Optional<Order> createOrderMock() {
        Order order = new Order();
        order.setId(1L);
        order.setInvoiceNumber(UUID.randomUUID().toString());
        order.setTotalAmount(1000);
        order.setNote("test");
        order.setOrderDateTime(new Date());
        order.setPaymentExpiredDateTime(new Date());
        order.setPayer(UserDataMock.createUserMock());
        OrderPayment orderPayment = new OrderPayment();
        orderPayment.setOrder(order);
        orderPayment.setPaymentMethod("test");
        orderPayment.setId(1L);
        order.setOrderPayment(orderPayment);
        OrderAddress orderAddress = new OrderAddress();
        order.setOrderAddress(orderAddress);
        ArrayList<OrderProduct> products = new ArrayList<>();
        OrderProduct orderProduct = new OrderProduct();
        orderProduct.setId(1L);
        orderProduct.setProduct(ProductDataMock.getProduct());
        orderProduct.setOrder(order);
        orderProduct.setQuantity(1);
        orderProduct.setAmount(1000);
        products.add(orderProduct);
        order.setOrderProducts(products);
        return Optional.of(order);
    }

}
