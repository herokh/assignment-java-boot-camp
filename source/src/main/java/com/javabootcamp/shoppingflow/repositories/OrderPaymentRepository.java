package com.javabootcamp.shoppingflow.repositories;

import com.javabootcamp.shoppingflow.models.OrderPayment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderPaymentRepository extends JpaRepository<OrderPayment, Long> {
}
