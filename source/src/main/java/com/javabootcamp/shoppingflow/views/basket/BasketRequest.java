package com.javabootcamp.shoppingflow.views.basket;

import lombok.Getter;
import lombok.Setter;

public class BasketRequest {
    private @Getter @Setter long productId;
    private @Getter @Setter int quantity;
}
