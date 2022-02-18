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
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ProductListResponse getProductList(@RequestParam("page") Optional<Integer> pageNumber,
                                              @RequestParam("name") Optional<String> productNameSearch) {
        ProductListResponse productList = productService.getProductList(pageNumber, productNameSearch);
        return productList;
    }

    @GetMapping(value = "/{productId}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ProductDetailResponse getProductDetail(@PathVariable long productId) {
        ProductDetailResponse productDetail = productService.getProductDetail(productId);
        return productDetail;
    }
}
