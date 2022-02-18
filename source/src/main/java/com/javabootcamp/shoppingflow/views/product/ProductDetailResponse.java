package com.javabootcamp.shoppingflow.views.product;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

public class ProductDetailResponse{
	private @Getter @Setter Date promotionEndDate;
	private @Getter @Setter double price;
	private @Getter @Setter String imageUrl;
	private @Getter @Setter List<String> imageThumbnailUrls;
	private @Getter @Setter String name;
	private @Getter @Setter float rating;
	private @Getter @Setter double netPrice;
	private @Getter @Setter long id;
	private @Getter @Setter int totalReviewer;
	private @Getter @Setter ProductCategoryResponse category;
	private @Getter @Setter ProductBrandResponse brand;
}