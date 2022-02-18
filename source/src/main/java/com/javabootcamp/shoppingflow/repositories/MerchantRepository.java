package com.javabootcamp.shoppingflow.repositories;

import com.javabootcamp.shoppingflow.models.Merchant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MerchantRepository extends JpaRepository<Merchant, Long> {
}
