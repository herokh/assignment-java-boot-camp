package com.javabootcamp.shoppingflow.repositories;

import com.javabootcamp.shoppingflow.models.OrderAddress;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderAddressRepository extends JpaRepository<OrderAddress, Long> {
}
