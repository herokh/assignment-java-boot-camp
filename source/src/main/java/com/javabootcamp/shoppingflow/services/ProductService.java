package com.javabootcamp.shoppingflow.services;

import com.javabootcamp.shoppingflow.exceptions.ProductNotFoundException;
import com.javabootcamp.shoppingflow.models.Address;
import com.javabootcamp.shoppingflow.models.Merchant;
import com.javabootcamp.shoppingflow.models.Product;
import com.javabootcamp.shoppingflow.repositories.ProductRepository;
import com.javabootcamp.shoppingflow.views.common.AddressResponse;
import com.javabootcamp.shoppingflow.views.common.MerchantResponse;
import com.javabootcamp.shoppingflow.views.product.ProductItemResponse;
import com.javabootcamp.shoppingflow.views.product.ProductListResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private static final int PAGE_SIZE = 50;

    @Autowired
    private ProductRepository productRepository;

    public ProductListResponse getProductList(Optional<Integer> pageNumber, Optional<String> productNameSearch) {
        Pageable pageable = PageRequest.of(pageNumber.isPresent() ? pageNumber.get() : 0, PAGE_SIZE);
        Page<Product> productsPaged = productRepository.findByNameContaining(
                productNameSearch.isPresent() ? productNameSearch.get() : "", pageable);

        ProductListResponse productListResponse = new ProductListResponse();
        if (productsPaged.hasContent()) {
            productListResponse.setPage(productsPaged.getNumber() + 1);
            productListResponse.setPageSize(productsPaged.getSize());
            productListResponse.setTotalPages(productsPaged.getTotalPages());
            productListResponse.setTotalItems(productsPaged.getTotalElements());
            productListResponse.setResult(mapProductResultView(productsPaged));
        }
        else {
            throw new ProductNotFoundException("Product is empty.");
        }
        return productListResponse;
    }

    private List<ProductItemResponse> mapProductResultView(Page<Product> productsPaged) {
        Page<ProductItemResponse> result = productsPaged.map(product -> {
            ProductItemResponse productView = new ProductItemResponse();
            productView.setId(product.getId());
            productView.setName(product.getName());
            productView.setPrice(product.getPrice());
            productView.setNetPrice(product.getNetPrice());
            productView.setImageUrl(product.getImageUrl());
            productView.setRating(product.getRating());
            productView.setTotalReviewer(product.getTotalReviewer());

            Merchant merchant = product.getMerchant();
            MerchantResponse merchantView = new MerchantResponse();

            Address address = merchant.getAddress();
            AddressResponse addressView = new AddressResponse();

            addressView.setProvince(address.getProvince());
            merchantView.setAddress(addressView);
            productView.setMerchant(merchantView);

            return productView;
        });

        return result.toList();
    }

}
