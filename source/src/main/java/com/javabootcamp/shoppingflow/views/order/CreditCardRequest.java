package com.javabootcamp.shoppingflow.views.order;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreditCardRequest {
	private String cardName;
	private String expiredMonth;
	private String cardCcv;
	private String expiredYear;
	private String cardNumber;
}
