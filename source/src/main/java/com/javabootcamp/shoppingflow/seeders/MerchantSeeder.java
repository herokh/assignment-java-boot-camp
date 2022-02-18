package com.javabootcamp.shoppingflow.seeders;

import com.javabootcamp.shoppingflow.models.Address;
import com.javabootcamp.shoppingflow.models.Merchant;
import com.javabootcamp.shoppingflow.repositories.MerchantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class MerchantSeeder {

    public static final Long DEFAULT_MERCHANT_ID = 1L;

    @Autowired
    private AddressSeeder addressSeeder;

    @Autowired
    private MerchantRepository merchantRepository;

    public void createMerchantMock() {
        Address defaultAddress = addressSeeder.getDefaultAddressMock();

        Merchant merchant = new Merchant();
        merchant.setId(DEFAULT_MERCHANT_ID);
        merchant.setAddress(defaultAddress);
        merchantRepository.save(merchant);
    }

    public Merchant getDefaultMerchantMock() {
        Optional<Merchant> merchant = merchantRepository.findById(DEFAULT_MERCHANT_ID);
        return merchant.get();
    }

}
