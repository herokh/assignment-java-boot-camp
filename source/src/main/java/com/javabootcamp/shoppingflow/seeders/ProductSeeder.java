package com.javabootcamp.shoppingflow.seeders;

import com.javabootcamp.shoppingflow.models.Merchant;
import com.javabootcamp.shoppingflow.models.Product;
import com.javabootcamp.shoppingflow.models.ProductBrand;
import com.javabootcamp.shoppingflow.models.ProductCategory;
import com.javabootcamp.shoppingflow.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Random;

@Component
public class ProductSeeder {

    @Autowired
    private MerchantSeeder merchantSeeder;

    @Autowired
    private ProductBrandSeeder productBrandSeeder;

    @Autowired
    private ProductCategorySeeder productCategorySeeder;

    @Autowired
    private ProductRepository productRepository;

    @Transactional
    public void createProductsMock() {
        Merchant merchant = merchantSeeder.getDefaultMerchantMock();
        ProductBrand productBrand = productBrandSeeder.getDefaultProductBrandMock();
        ProductCategory productCategory = productCategorySeeder.getDefaultProductCategoryMock();

        ArrayList<Product> products = new ArrayList<>();
        int discountRate = 20;
        var totalProducts = 100;
        for (int i = 1; i <= totalProducts; i++) {
            Product productMock = new Product();

            productMock.setId((long) i);
            productMock.setName(String.format("Product %d", i));
            productMock.setPrice(new Random().nextDouble() * 1000);
            double discountAmount = productMock.getPrice() * discountRate / 100;
            productMock.setNetPrice(productMock.getPrice() - discountAmount);
            productMock.setRating(new Random().nextFloat() * 100);
            productMock.setTotalReviewer(Math.abs(new Random().nextInt()));
            productMock.setImageUrl(String.format("https://images.com/product-%d-main.png", i));
            productMock.setMerchant(merchant);
            productMock.setProductBrand(productBrand);
            productMock.setProductCategory(productCategory);

            products.add(productMock);
        }
        productRepository.saveAll(products);
    }

}
