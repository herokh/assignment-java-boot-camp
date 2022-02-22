package com.javabootcamp.shoppingflow.services;

import com.javabootcamp.shoppingflow.models.Payment;
import com.javabootcamp.shoppingflow.repositories.PaymentRepository;
import com.javabootcamp.shoppingflow.views.payment.PaymentResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.lenient;

@ExtendWith(MockitoExtension.class)
public class PaymentServiceTests {

    private PaymentService paymentServiceTest;

    @Mock
    private PaymentRepository paymentRepositoryMock;

    @BeforeEach
    void setup () {
        lenient().when(paymentRepositoryMock.findAll()).thenReturn(createPaymentListMock());

        paymentServiceTest = new PaymentService(paymentRepositoryMock);
    }

    @Test
    void getPaymentsShouldBeSuccess() {
        // Act
        List<PaymentResponse> result = paymentServiceTest.getPayments();

        // Assert
        assertNotNull(result);
    }

    private List<Payment> createPaymentListMock() {
        List<Payment> paymentList = new ArrayList<Payment>();
        Payment payment = new Payment();
        payment.setId(1L);
        payment.setPaymentMethodName("test");
        payment.setPaymentMethodSlug("test");
        payment.setEnabled(true);
        paymentList.add(payment);
        return paymentList;
    }

}
