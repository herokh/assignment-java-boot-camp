package com.javabootcamp.shoppingflow.controllers;

import com.javabootcamp.shoppingflow.services.BasketService;
import com.javabootcamp.shoppingflow.services.OrderService;
import com.javabootcamp.shoppingflow.views.basket.BasketRequest;
import com.javabootcamp.shoppingflow.views.order.OrderRequest;
import com.javabootcamp.shoppingflow.views.order.OrderResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/secure/orders")
public class OrdersController implements SecuredRestController {

    @Autowired
    private OrderService orderService;

    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public void createNewOrder(@RequestBody OrderRequest request) {
        orderService.createNewOrder(request);
    }

    @GetMapping(
            value = "/{orderId}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public OrderResponse getUserOrder(@PathVariable long orderId) {
        return orderService.getUserOrder(orderId);
    }

}
