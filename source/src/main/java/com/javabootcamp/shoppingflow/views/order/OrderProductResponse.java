package com.javabootcamp.shoppingflow.views.order;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class OrderProductResponse {
    private long id;
    private String name;
    private double amount;
    private int quantity;
}
