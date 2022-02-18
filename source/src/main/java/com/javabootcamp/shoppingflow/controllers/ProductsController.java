package com.javabootcamp.shoppingflow.controllers;

import com.javabootcamp.shoppingflow.services.ProductService;
import com.javabootcamp.shoppingflow.views.product.ProductDetailResponse;
import com.javabootcamp.shoppingflow.views.product.ProductListResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/products")
public class ProductsController {

    @Autowired
    private ProductService productService;

    @GetMapping(
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ProductListResponse getProductList(@RequestParam("page") Optional<Integer> pageNumber,
                                              @RequestParam("name") Optional<String> searchTerm) {
        ProductListResponse productList = productService.getProductList(pageNumber, searchTerm);
        return productList;
    }

    @GetMapping(value = "/{productId}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ProductDetailResponse getProductDetail(@PathVariable long productId) {
        ProductDetailResponse productDetail = productService.getProductDetail(productId);
        return productDetail;
    }
}
