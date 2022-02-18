package com.javabootcamp.shoppingflow.services;

import com.javabootcamp.shoppingflow.ApplicationContext;
import com.javabootcamp.shoppingflow.models.Basket;
import com.javabootcamp.shoppingflow.models.User;
import com.javabootcamp.shoppingflow.repositories.BasketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BasketService {

    @Autowired
    private BasketRepository basketRepository;

    @Autowired
    private ApplicationContext applicationContext;

    public void addToBasket(long productId, int quantity) {
        User currentUser = applicationContext.getCurrentUser();
        Basket basket = new Basket();
        basket.setProductId(productId);
        basket.setQuantity(quantity);
        basket.setUser(currentUser);
        basketRepository.save(basket);
    }

}
