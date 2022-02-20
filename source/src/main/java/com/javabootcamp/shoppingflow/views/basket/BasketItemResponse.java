package com.javabootcamp.shoppingflow.views.basket;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class BasketItemResponse {
    private long productId;
    private String name;
    private String imageUrl;
    private double price;
    private double netPrice;
    private int quantity;
    private List<BasketAdditionalInfoResponse> additionalInfos;
    private BasketBrandResponse brand;
}
