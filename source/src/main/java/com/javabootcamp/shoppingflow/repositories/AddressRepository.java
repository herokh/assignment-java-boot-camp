package com.javabootcamp.shoppingflow.repositories;

import com.javabootcamp.shoppingflow.models.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
}
