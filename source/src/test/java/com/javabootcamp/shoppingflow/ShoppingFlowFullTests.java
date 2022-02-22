package com.javabootcamp.shoppingflow;

import com.javabootcamp.shoppingflow.utils.DateTimeUtil;
import com.javabootcamp.shoppingflow.views.auth.AuthRequest;
import com.javabootcamp.shoppingflow.views.auth.AuthResponse;
import com.javabootcamp.shoppingflow.views.basket.BasketRequest;
import com.javabootcamp.shoppingflow.views.basket.BasketResponse;
import com.javabootcamp.shoppingflow.views.order.NewOrderResponse;
import com.javabootcamp.shoppingflow.views.order.OrderRequest;
import com.javabootcamp.shoppingflow.views.order.OrderResponse;
import com.javabootcamp.shoppingflow.views.payment.PaymentResponse;
import com.javabootcamp.shoppingflow.views.product.ProductItemResponse;
import com.javabootcamp.shoppingflow.views.product.ProductListResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ShoppingFlowFullTests {
    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    @DisplayName("This is the full test running to verify the whole system that is still work fine")
    void shoppingFlowFullTestShouldBeSuccess() {
        // Phase 1 : Signin

        // Arrange
        AuthRequest authRequest = new AuthRequest();
        authRequest.setUsername("hero");
        authRequest.setPassword("1234");

        // Act
        AuthResponse authResponse = assertDoesNotThrow(() ->
                testRestTemplate.postForObject("/api/auth/signin", authRequest, AuthResponse.class));

        // Assert
        assertNotNull(authResponse);
        String userToken = authResponse.getToken();
        assertNotNull(userToken);
        HttpEntity<Class<Void>> requestHttpGetEntity = ShoppingFlowTestUtil.createRequestHttpEntity(userToken, Void.class);

        // Phase 2 : List all products

        // Act
        ProductListResponse productListResponse = assertDoesNotThrow(() ->
                testRestTemplate.getForObject("/api/products", ProductListResponse.class));

        // Assert
        assertNotNull(productListResponse);
        List<ProductItemResponse> result = productListResponse.getResult();
        assertNotNull(result);

        // Phase 3 : Pick 1 product into the basket

        // Arrange
        ProductItemResponse productItemResponse = result.stream()
                .filter(product -> product.getId().equals(1L))
                .findFirst()
                .orElse(null);
        assertNotNull(productItemResponse);
        BasketRequest basketRequest = new BasketRequest();
        basketRequest.setProductId(productItemResponse.getId());
        basketRequest.setQuantity(3);
        HttpEntity<BasketRequest> basketSecureRequest = ShoppingFlowTestUtil.createRequestHttpEntity(userToken, basketRequest);

        // Act & Assert
        assertDoesNotThrow(() -> testRestTemplate.postForObject("/api/secure/baskets", basketSecureRequest, Void.class));

        // Phase 4 : Check item in the basket

        // Act
        BasketResponse basketResponse = assertDoesNotThrow(() -> testRestTemplate.exchange("/api/secure/baskets", HttpMethod.GET, requestHttpGetEntity, BasketResponse.class).getBody());

        // Assert
        assertNotNull(basketResponse);
        assertEquals(1, basketResponse.getBasketItems().size());
        assertEquals(3, basketResponse.getTotalItems());
        assertTrue(basketResponse.getTotalAmount() > 0);
        assertTrue(basketResponse.getTotalNetAmount() > 0);

        // Phase 5 : List all payment and select 1 payment method

        // Act
        var paymentResponse = assertDoesNotThrow(() ->
                testRestTemplate.exchange("/api/secure/payments", HttpMethod.GET, requestHttpGetEntity, PaymentResponse[].class).getBody());

        // Assert
        assertNotNull(paymentResponse);
        PaymentResponse selectedPayment = null;
        for (var payment: paymentResponse) {
            if ("direct-debit".equals(payment.getSlug())) {
                selectedPayment = payment;
                break;
            }
        }
        assertNotNull(selectedPayment);

        // Phase 6 : Create new order

        // Arrange
        OrderRequest orderRequest = new OrderRequest();
        orderRequest.setNote("Test");
        orderRequest.setPaymentMethod(selectedPayment.getSlug());
        HttpEntity<OrderRequest> orderSecureRequest = ShoppingFlowTestUtil.createRequestHttpEntity(userToken, orderRequest);

        // Act
        NewOrderResponse newOrderResponse = assertDoesNotThrow(() -> testRestTemplate.postForObject("/api/secure/orders", orderSecureRequest, NewOrderResponse.class));

        // Assert
        assertNotNull(newOrderResponse);
        long newOrderId = newOrderResponse.getId();
        assertTrue(newOrderId > 0);

        // Phase 7 : See the order summary

        // Act
        OrderResponse orderResponse = assertDoesNotThrow(() ->
                testRestTemplate.exchange(String.format("/api/secure/orders/%d", newOrderId), HttpMethod.GET, requestHttpGetEntity, OrderResponse.class).getBody());

        assertNotNull(orderResponse);
        assertNotNull(orderResponse.getInvoiceNumber());

        assertEquals(newOrderId, orderResponse.getId());
        assertEquals(orderRequest.getNote(), orderResponse.getNote());

        assertTrue(orderResponse.getTotalAmount() > 0);

        final String DATE_FORMAT = "yyyy-MM-dd";
        assertEquals(DateTimeUtil.convertDateToString(new Date(), DATE_FORMAT),
                DateTimeUtil.convertDateToString(orderResponse.getOrderDateTime(), DATE_FORMAT));

        final int NUMBER_OF_DAYS = 3;
        assertEquals(DateTimeUtil.convertDateToString(DateTimeUtil.addDay(new Date(), NUMBER_OF_DAYS), DATE_FORMAT),
                DateTimeUtil.convertDateToString(orderResponse.getPaymentExpiredDateTime(), DATE_FORMAT));

        assertEquals(1, orderResponse.getResult().size());
    }
}
