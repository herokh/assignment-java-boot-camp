package com.javabootcamp.shoppingflow.repositories;

import com.javabootcamp.shoppingflow.models.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Long> {
}
