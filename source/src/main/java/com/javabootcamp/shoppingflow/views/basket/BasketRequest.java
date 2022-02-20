package com.javabootcamp.shoppingflow.views.basket;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BasketRequest {
    private long productId;
    private int quantity;
}
