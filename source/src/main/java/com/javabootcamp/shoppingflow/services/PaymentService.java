package com.javabootcamp.shoppingflow.services;

import com.javabootcamp.shoppingflow.repositories.PaymentRepository;
import com.javabootcamp.shoppingflow.views.payment.PaymentResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    public List<PaymentResponse> getPayments() {
        var payments = paymentRepository.findAll();
        var paymentViews = new ArrayList<PaymentResponse>();
        for (var p: payments) {
            var paymentView = new PaymentResponse();
            paymentView.setName(p.getPaymentMethodName());
            paymentView.setSlug(p.getPaymentMethodSlug());
            paymentView.setEnabled(p.isEnabled());
            paymentViews.add(paymentView);
        }
        return paymentViews;
    }

}
