package com.javabootcamp.shoppingflow.seeders;

import com.javabootcamp.shoppingflow.models.ProductCategory;
import com.javabootcamp.shoppingflow.repositories.ProductCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Component
public class ProductCategorySeeder {

    private static final long PRODUCT_CATEGORY_ID = 1L;

    @Autowired
    private ProductCategoryRepository productCategoryRepository;

    public void createProductCategoryMock() {
        ProductCategory productCategory = new ProductCategory();
        productCategory.setId(PRODUCT_CATEGORY_ID);
        productCategory.setName("Category 1");

        productCategoryRepository.save(productCategory);
    }

    public ProductCategory getDefaultProductCategoryMock() {
        Optional<ProductCategory> productCategory = productCategoryRepository.findById(PRODUCT_CATEGORY_ID);
        return productCategory.get();
    }
}
