package com.javabootcamp.shoppingflow.views.payment;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentResponse {
    private String name;
    private String slug;
    private boolean isEnabled;
}
