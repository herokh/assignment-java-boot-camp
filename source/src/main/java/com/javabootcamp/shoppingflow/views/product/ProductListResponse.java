package com.javabootcamp.shoppingflow.views.product;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class ProductListResponse{
	private @Getter @Setter int page;
	private @Getter @Setter int pageSize;
	private @Getter @Setter int totalPages;
	private @Getter @Setter Long totalItems;

	private @Getter @Setter List<ProductItemResponse> result;
}