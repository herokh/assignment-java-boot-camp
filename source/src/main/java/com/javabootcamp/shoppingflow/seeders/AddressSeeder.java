package com.javabootcamp.shoppingflow.seeders;

import com.javabootcamp.shoppingflow.models.Address;
import com.javabootcamp.shoppingflow.repositories.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AddressSeeder {

    public static final Long DEFAULT_ADDRESS_ID = 1L;

    @Autowired
    private AddressRepository addressRepository;

    public void createAddressMock() {
        Address address = new Address();
        address.setId(DEFAULT_ADDRESS_ID);
        address.setProvince("Bangkok");
        addressRepository.save(address);
    }

    public Address getDefaultAddressMock() {
        Optional<Address> address = addressRepository.findById(DEFAULT_ADDRESS_ID);
        return address.get();
    }
}