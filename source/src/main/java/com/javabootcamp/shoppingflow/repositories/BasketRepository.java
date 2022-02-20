package com.javabootcamp.shoppingflow.repositories;

import com.javabootcamp.shoppingflow.models.Basket;
import com.javabootcamp.shoppingflow.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BasketRepository extends JpaRepository<Basket, Long> {
    Optional<Basket> findByUser(User user);
}
