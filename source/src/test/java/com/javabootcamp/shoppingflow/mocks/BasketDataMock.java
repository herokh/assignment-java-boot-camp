package com.javabootcamp.shoppingflow.mocks;

import com.javabootcamp.shoppingflow.models.Basket;
import com.javabootcamp.shoppingflow.models.BasketProduct;
import com.javabootcamp.shoppingflow.views.basket.BasketBrandResponse;
import com.javabootcamp.shoppingflow.views.basket.BasketItemResponse;
import com.javabootcamp.shoppingflow.views.basket.BasketResponse;

import java.util.ArrayList;

public class BasketDataMock {

    public static Basket createUserBasketMock() {
        var basket = new Basket();
        basket.setId(1L);
        basket.setUser(UserDataMock.createUserMock());
        var products = new ArrayList<BasketProduct>();
        BasketProduct basketProduct = new BasketProduct();
        basketProduct.setProduct(ProductDataMock.getProduct());
        basketProduct.setBasket(basket);
        basketProduct.setQuantity(1);
        basketProduct.setId(1L);
        products.add(basketProduct);
        basket.setBasketProducts(products);
        return basket;
    }

    public static BasketResponse createUserBasketResponseMock() {
        var basket = new BasketResponse();
        basket.setTotalItems(1);
        basket.setTotalAmount(100);
        basket.setTotalNetAmount(50);
        var basketItemResponses = new ArrayList<BasketItemResponse>();
        var basketItemResponse = new BasketItemResponse();
        basketItemResponse.setProductId(1L);
        basketItemResponse.setName("test 1");
        basketItemResponse.setPrice(100);
        basketItemResponse.setNetPrice(50);
        basketItemResponse.setImageUrl("image");
        basketItemResponse.setQuantity(1);
        basketItemResponse.setBrand(new BasketBrandResponse());
        basketItemResponse.setAdditionalInfos(null);
        basketItemResponses.add(basketItemResponse);
        basket.setBasketItems(basketItemResponses);

        return basket;
    }

}
