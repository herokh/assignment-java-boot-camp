package com.javabootcamp.shoppingflow.seeders;

import com.javabootcamp.shoppingflow.models.ProductBrand;
import com.javabootcamp.shoppingflow.repositories.ProductBrandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ProductBrandSeeder {

    private static final long PRODUCT_BRAND_ID = 1L;

    @Autowired
    private ProductBrandRepository productBrandRepository;

    public void createProductBrandMock() {
        ProductBrand productBrand = new ProductBrand();
        productBrand.setId(PRODUCT_BRAND_ID);
        productBrand.setName("Brand 1");

        productBrandRepository.save(productBrand);
    }

    public ProductBrand getDefaultProductBrandMock() {
        Optional<ProductBrand> productBrand = productBrandRepository.findById(PRODUCT_BRAND_ID);
        return productBrand.get();
    }

}
