package com.javabootcamp.shoppingflow.repositories;

import com.javabootcamp.shoppingflow.models.Basket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BasketRepository extends JpaRepository<Basket, Long> {
}
