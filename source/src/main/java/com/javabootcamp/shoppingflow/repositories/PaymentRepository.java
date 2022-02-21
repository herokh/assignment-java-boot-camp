package com.javabootcamp.shoppingflow.repositories;

import com.javabootcamp.shoppingflow.models.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
    Optional<Payment> findByPaymentMethodSlug(String paymentMethodSlug);
}
