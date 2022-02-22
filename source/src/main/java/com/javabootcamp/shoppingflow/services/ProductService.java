package com.javabootcamp.shoppingflow.services;

import com.javabootcamp.shoppingflow.exceptions.ProductNotFoundException;
import com.javabootcamp.shoppingflow.models.*;
import com.javabootcamp.shoppingflow.repositories.ProductRepository;
import com.javabootcamp.shoppingflow.views.common.AddressResponse;
import com.javabootcamp.shoppingflow.views.common.MerchantResponse;
import com.javabootcamp.shoppingflow.views.product.*;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@NoArgsConstructor
public class ProductService {

    private static final int PAGE_SIZE = 50;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Autowired
    private ProductRepository productRepository;

    public ProductListResponse getProductList(Optional<Integer> pageNumber, Optional<String> searchTerm) {
        int pageNumberValue = pageNumber.isPresent() ? pageNumber.get() - 1 : 0;
        if (pageNumberValue < 0)
            pageNumberValue = 0;
        Pageable pageable = PageRequest.of(pageNumberValue, PAGE_SIZE);
        Page<Product> productsPaged = productRepository.findByNameContaining(
                searchTerm.isPresent() ? searchTerm.get() : "", pageable);

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

    public ProductDetailResponse getProductDetail(long productId) {
        Optional<Product> product = productRepository.findById(productId);
        if (product.isPresent()) {
            Product productDomain = product.get();
            ProductDetailResponse productView = new ProductDetailResponse();
            productView.setId(productDomain.getId());
            productView.setName(productDomain.getName());
            productView.setImageUrl(productDomain.getImageUrl());
            productView.setPrice(productDomain.getPrice());
            productView.setNetPrice(productDomain.getNetPrice());
            productView.setRating(productDomain.getRating());
            productView.setTotalReviewer(productDomain.getTotalReviewer());
            productView.setImageThumbnailUrls(new ArrayList<>()); // TODO: The thumbnails feature is not implemented yet.
            productView.setPromotionEndDate(null); // TODO: The promotion feature is not implemeted yet.

            ProductBrand productBrand = productDomain.getProductBrand();
            ProductBrandResponse productBrandView = new ProductBrandResponse();
            productBrandView.setId(productBrand.getId());
            productBrandView.setName(productBrand.getName());
            productView.setBrand(productBrandView);

            ProductCategory productCategory = productDomain.getProductCategory();
            ProductCategoryResponse productCategoryView = new ProductCategoryResponse();
            productCategoryView.setId(productCategory.getId());
            productCategoryView.setName(productCategory.getName());
            productView.setCategory(productCategoryView);

            return productView;
        }
        throw new ProductNotFoundException(String.format("Product id %d is not found.", productId));
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
