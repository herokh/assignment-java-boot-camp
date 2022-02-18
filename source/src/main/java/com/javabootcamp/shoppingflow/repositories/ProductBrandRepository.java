package com.javabootcamp.shoppingflow.repositories;

import com.javabootcamp.shoppingflow.models.ProductBrand;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductBrandRepository extends JpaRepository<ProductBrand, Long> {
}
