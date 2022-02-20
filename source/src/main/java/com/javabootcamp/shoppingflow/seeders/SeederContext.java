package com.javabootcamp.shoppingflow.seeders;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class SeederContext {

    @Autowired
    private UserSeeder userSeeder;

    @Autowired
    private AddressSeeder addressSeeder;

    @Autowired
    private MerchantSeeder merchantSeeder;

    @Autowired
    private ProductBrandSeeder productBrandSeeder;

    @Autowired
    private ProductCategorySeeder productCategorySeeder;

    @Autowired
    private ProductSeeder productSeeder;

    @Autowired
    private PaymentSeeder paymentSeeder;

    @Transactional
    public void createDataMocks() {
        addressSeeder.createAddressMock();
        userSeeder.createUserMock();
        merchantSeeder.createMerchantMock();
        productBrandSeeder.createProductBrandMock();
        productCategorySeeder.createProductCategoryMock();
        productSeeder.createProductsMock();
        paymentSeeder.createPaymentMock();
    }
}
