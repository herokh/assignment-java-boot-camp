package com.javabootcamp.shoppingflow.services;

import com.javabootcamp.shoppingflow.models.*;
import com.javabootcamp.shoppingflow.repositories.ProductRepository;
import com.javabootcamp.shoppingflow.views.product.ProductDetailResponse;
import com.javabootcamp.shoppingflow.views.product.ProductListResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTests {

    private ProductService productServiceTest;

    @Mock
    private ProductRepository productRepositoryMock;

    @BeforeEach
    void setup() {
        lenient().when(productRepositoryMock.findByNameContaining(Mockito.anyString(), Mockito.any(Pageable.class)))
                .thenReturn(createProductListMock());
        lenient().when(productRepositoryMock.findById(Mockito.anyLong()))
                .thenReturn(createSingleProductMock());

        productServiceTest = new ProductService(productRepositoryMock);
    }

    @Test
    void getProductListShouldBeSuccess() {
        // Act
        ProductListResponse result = assertDoesNotThrow(() ->
                productServiceTest.getProductList(Optional.of(1), Optional.of("test")));
    
        // Assert
        assertNotNull(result);
    }


    @Test
    void getProductDetailShouldBeSuccess() {
        // Act
        ProductDetailResponse result = assertDoesNotThrow(() ->
                productServiceTest.getProductDetail(1L));

        // Assert
        assertNotNull(result);
    }

    private Optional<Product> createSingleProductMock() {
        var product = getProduct();
        return Optional.of(product);
    }

    private Page<Product> createProductListMock() {
        var products = new ArrayList<Product>();
        var product = getProduct();
        products.add(product);
        Page<Product> productPage = new PageImpl<Product>(products);

        return productPage;
    }

    private Product getProduct() {
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
