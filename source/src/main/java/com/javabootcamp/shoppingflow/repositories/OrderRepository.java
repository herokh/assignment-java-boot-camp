package com.javabootcamp.shoppingflow.repositories;

import com.javabootcamp.shoppingflow.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
