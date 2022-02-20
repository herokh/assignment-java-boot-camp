package com.javabootcamp.shoppingflow.controllers;

import com.javabootcamp.shoppingflow.services.PaymentService;
import com.javabootcamp.shoppingflow.views.payment.PaymentResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/secure/payments")
public class PaymentsController implements SecuredRestController {

    @Autowired
    private PaymentService paymentService;

    @GetMapping(
            produces = MediaType.APPLICATION_JSON_VALUE)
    public List<PaymentResponse> getPayments() {
        var paymentViews = paymentService.getPayments();
        return paymentViews;
    }
}
