package com.javabootcamp.shoppingflow.controllers;

import com.javabootcamp.shoppingflow.services.BasketService;
import com.javabootcamp.shoppingflow.views.basket.BasketRequest;
import com.javabootcamp.shoppingflow.views.basket.BasketResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/secure/baskets")
public class BasketsController implements SecuredRestController {

    @Autowired
    private BasketService basketService;

    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public void addToBasket(@RequestBody BasketRequest request) {
        basketService.updateUserBasket(request.getProductId(), request.getQuantity());
    }

    @GetMapping(
            produces = MediaType.APPLICATION_JSON_VALUE)
    public BasketResponse getUserBasket() {
        return basketService.getUserBasket();
    }
}
