package com.javabootcamp.shoppingflow.views.product;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ProductListResponse{
	private int page;
	private int pageSize;
	private int totalPages;
	private Long totalItems;

	private List<ProductItemResponse> result;
}