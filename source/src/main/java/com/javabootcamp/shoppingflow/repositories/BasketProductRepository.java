package com.javabootcamp.shoppingflow.repositories;

import com.javabootcamp.shoppingflow.models.BasketProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BasketProductRepository extends JpaRepository<BasketProduct, Long> {
}
