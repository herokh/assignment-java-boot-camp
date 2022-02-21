package com.javabootcamp.shoppingflow.views.order;

import lombok.Getter;
import lombok.Setter;

import java.util.Optional;

@Getter
@Setter
public class OrderRequest{
	private String paymentMethod;
	private Optional<CreditCardRequest> creditCard;
}
