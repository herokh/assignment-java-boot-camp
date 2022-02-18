package com.javabootcamp.shoppingflow.seeders;

import com.javabootcamp.shoppingflow.models.Merchant;
import com.javabootcamp.shoppingflow.models.Product;
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
    private ProductRepository productRepository;

    @Transactional
    public void createProductsMock() {
        Merchant merchant = merchantSeeder.getDefaultMerchantMock();

        ArrayList<Product> products = new ArrayList<>();
        int discountRate = 20;
        var totalProducts = 100;
        for (int i = 1; i <= totalProducts; i++) {
            Product productMock = new Product();

            productMock.setId((long) i);
            productMock.setName(String.format("Product %d", i));
            productMock.setPrice(new Random(1000).nextDouble() * 1000);
            double discountAmount = productMock.getPrice() * discountRate / 100;
            productMock.setNetPrice(productMock.getPrice() - discountAmount);
            productMock.setRating(new Random(10).nextFloat() * 100);
            productMock.setTotalReviewer(Math.abs(new Random(10).nextInt()));
            productMock.setImageUrl(String.format("https://images.com/product-%d-main.png", i));
            productMock.setMerchant(merchant);

            products.add(productMock);
        }
        productRepository.saveAll(products);
    }

}
