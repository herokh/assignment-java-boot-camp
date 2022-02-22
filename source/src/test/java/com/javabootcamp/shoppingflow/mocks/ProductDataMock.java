package com.javabootcamp.shoppingflow.mocks;

import com.javabootcamp.shoppingflow.models.*;

public class ProductDataMock {

    public static Product getProduct() {
        Product product = new Product();
        product.setId(1L);
        product.setName("test 1");
        product.setPrice(100);
        product.setNetPrice(50);
        product.setImageUrl("image");
        product.setTotalReviewer(5);
        product.setRating(5F);
        ProductBrand productBrand = new ProductBrand();
        productBrand.setId(1L);
        productBrand.setName("brand 1");
        product.setProductBrand(productBrand);
        ProductCategory productCategory = new ProductCategory();
        productCategory.setId(1L);
        productBrand.setName("category 1");
        product.setProductCategory(productCategory);
        Merchant merchant = new Merchant();
        merchant.setAddress(new Address());
        product.setMerchant(merchant);
        return product;
    }
}
