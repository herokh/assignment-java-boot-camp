package com.javabootcamp.shoppingflow.repositories;

import com.javabootcamp.shoppingflow.models.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
}
