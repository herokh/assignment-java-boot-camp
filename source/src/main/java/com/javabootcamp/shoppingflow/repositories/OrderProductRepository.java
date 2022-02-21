package com.javabootcamp.shoppingflow.repositories;

import com.javabootcamp.shoppingflow.models.OrderProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderProductRepository extends JpaRepository<OrderProduct, Long> {
}
