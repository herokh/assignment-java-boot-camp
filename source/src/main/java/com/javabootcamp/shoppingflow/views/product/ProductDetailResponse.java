package com.javabootcamp.shoppingflow.views.product;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class ProductDetailResponse{
	private Date promotionEndDate;
	private double price;
	private String imageUrl;
	private List<String> imageThumbnailUrls;
	private String name;
	private float rating;
	private double netPrice;
	private long id;
	private int totalReviewer;
	private ProductCategoryResponse category;
	private ProductBrandResponse brand;
}