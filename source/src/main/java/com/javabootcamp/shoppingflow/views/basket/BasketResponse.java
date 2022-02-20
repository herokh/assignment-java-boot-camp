package com.javabootcamp.shoppingflow.views.basket;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class BasketResponse {
    private int totalItems;
    private double totalAmount;
    private double totalNetAmount;
    private List<BasketItemResponse> basketItems;
}
