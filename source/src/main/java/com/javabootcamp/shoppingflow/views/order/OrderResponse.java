package com.javabootcamp.shoppingflow.views.order;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class OrderResponse {
    private Long id;
    private String invoiceNumber;
    private double totalAmount;
    private Date orderDateTime;
    private Date paymentExpiredDateTime;
    private String note;

}
