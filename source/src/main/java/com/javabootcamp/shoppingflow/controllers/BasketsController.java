package com.javabootcamp.shoppingflow.controllers;

import com.javabootcamp.shoppingflow.services.BasketService;
import com.javabootcamp.shoppingflow.views.basket.BasketRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/secure/baskets")
public class BasketsController {

    @Autowired
    private BasketService basketService;

    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public void addToBasket(@RequestBody BasketRequest request) {
        basketService.addToBasket(request.getProductId(), request.getQuantity());
    }
}
