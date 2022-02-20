package com.javabootcamp.shoppingflow.views.product;

import com.javabootcamp.shoppingflow.views.common.MerchantResponse;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductItemResponse {
	private Long id;
	private String name;
	private String imageUrl;
	private double price;
	private double netPrice;
	private float rating;
	private int totalReviewer;
	private MerchantResponse merchant;
}
