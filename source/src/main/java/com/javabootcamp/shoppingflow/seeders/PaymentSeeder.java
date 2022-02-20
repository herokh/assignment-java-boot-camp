package com.javabootcamp.shoppingflow.seeders;

import com.javabootcamp.shoppingflow.models.Payment;
import com.javabootcamp.shoppingflow.repositories.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PaymentSeeder {

    @Autowired
    private PaymentRepository paymentRepository;

    public void createPaymentMock(){
        Payment creditCard = new Payment(1L, "Credit card", "credit-card", true);
        Payment cashOnDelivery = new Payment(2L, "Cash on delivery", "cash-on-delivery", true);
        Payment directDebit = new Payment(3L, "Direct debit", "direct-debit", true);

        paymentRepository.save(creditCard);
        paymentRepository.save(cashOnDelivery);
        paymentRepository.save(directDebit);
    }
}
