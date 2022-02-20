package com.javabootcamp.shoppingflow.services;

import com.javabootcamp.shoppingflow.ApplicationContext;
import com.javabootcamp.shoppingflow.exceptions.BasketEmptyException;
import com.javabootcamp.shoppingflow.exceptions.ProductNotFoundException;
import com.javabootcamp.shoppingflow.models.*;
import com.javabootcamp.shoppingflow.repositories.BasketProductRepository;
import com.javabootcamp.shoppingflow.repositories.BasketRepository;
import com.javabootcamp.shoppingflow.repositories.ProductRepository;
import com.javabootcamp.shoppingflow.views.basket.BasketBrandResponse;
import com.javabootcamp.shoppingflow.views.basket.BasketItemResponse;
import com.javabootcamp.shoppingflow.views.basket.BasketResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class BasketService {

    @Autowired
    private BasketRepository basketRepository;

    @Autowired
    private BasketProductRepository basketProductRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ApplicationContext applicationContext;

    @Transactional
    public void updateUserBasket(long productId, int quantity) {
        Optional<Product> product = productRepository.findById(productId);
        if (product.isPresent()) {
            User currentUser = applicationContext.getCurrentUser();
            Optional<Basket> userBasket = basketRepository.findByUser(currentUser);
            if (userBasket.isPresent()) {
                updateUserBasketProduct(productId, quantity, product, userBasket);
            }
            else {
                createNewUserBasket(quantity, product, currentUser);
            }
        }
        else {
            throw new ProductNotFoundException(String.format("Product %d not found", productId));
        }
    }

    public BasketResponse getUserBasket() {
        User currentUser = applicationContext.getCurrentUser();
        Optional<Basket> userBasket = basketRepository.findByUser(currentUser);
        if (userBasket.isPresent()) {
            var basket = userBasket.get();
            var basketProducts = basket.getBasketProducts();

            var totalItems = basketProducts.stream()
                    .mapToInt(x -> x.getQuantity())
                    .sum();
            var totalNetAmount = basketProducts.stream()
                    .mapToDouble(x -> x.getProduct().getNetPrice() * x.getQuantity())
                    .sum();
            var totalAmount = basketProducts.stream()
                    .mapToDouble(x -> x.getProduct().getPrice() * x.getQuantity())
                    .sum();

            BasketResponse basketView = new BasketResponse();
            basketView.setTotalItems(totalItems);
            basketView.setTotalAmount(totalAmount);
            basketView.setTotalNetAmount(totalNetAmount);
            basketView.setBasketItems(mapToBasketProductViews(basketProducts));

            return basketView;
        }
        else {
            throw new BasketEmptyException("User basket is empty.");
        }
    }

    private void createNewUserBasket(int quantity, Optional<Product> product, User currentUser) {
        var basket = new Basket();
        var basketProducts = new ArrayList<BasketProduct>();
        var basketProduct = new BasketProduct();
        basketProduct.setProduct(product.get());
        basketProduct.setQuantity(quantity);
        basketProduct.setBasket(basket);
        basketProducts.add(basketProduct);
        basket.setBasketProducts(basketProducts);
        basket.setUser(currentUser);
        basketRepository.save(basket);
    }

    private void updateUserBasketProduct(long productId, int quantity, Optional<Product> product, Optional<Basket> userBasket) {
        var basket = userBasket.get();
        var basketProducts = basket.getBasketProducts();
        BasketProduct basketProduct = basketProducts.stream()
                .filter(x -> productId == x.getProduct().getId())
                .findAny()
                .orElse(null);
        if (basketProduct == null) {
            basketProduct = new BasketProduct();
            basketProduct.setProduct(product.get());
            basketProduct.setQuantity(quantity);
            basketProduct.setBasket(basket);
        }
        else {
            basketProduct.setQuantity(basketProduct.getQuantity() + quantity);
        }
        basketProductRepository.save(basketProduct);
    }

    private ArrayList<BasketItemResponse> mapToBasketProductViews(List<BasketProduct> basketProducts) {
        var items = new ArrayList<BasketItemResponse>();
        for (var item: basketProducts) {
            BasketItemResponse basketItemView = new BasketItemResponse();
            basketItemView.setProductId(item.getProduct().getId());
            basketItemView.setName(item.getProduct().getName());
            basketItemView.setImageUrl(item.getProduct().getImageUrl());
            basketItemView.setPrice(item.getProduct().getPrice());
            basketItemView.setNetPrice(item.getProduct().getNetPrice());
            ProductBrand productBrand = item.getProduct().getProductBrand();
            BasketBrandResponse brandView = new BasketBrandResponse();
            brandView.setId(productBrand.getId());
            brandView.setName(productBrand.getName());
            basketItemView.setBrand(brandView);
            basketItemView.setQuantity(item.getQuantity());
            basketItemView.setAdditionalInfos(new ArrayList<>()); //TODO: This feature is not implemented yet.
            items.add(basketItemView);
        }
        return items;
    }
}
