package com.javabootcamp.shoppingflow.views.product;

import com.javabootcamp.shoppingflow.views.common.MerchantResponse;
import lombok.Getter;
import lombok.Setter;

public class ProductItemResponse {
	private @Getter @Setter Long id;
	private @Getter @Setter String name;
	private @Getter @Setter String imageUrl;
	private @Getter @Setter double price;
	private @Getter @Setter double netPrice;
	private @Getter @Setter float rating;
	private @Getter @Setter int totalReviewer;
	private @Getter @Setter MerchantResponse merchant;
}
