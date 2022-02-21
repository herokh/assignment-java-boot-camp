package com.javabootcamp.shoppingflow.views.order;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class OrderResponse {
    private Long id;
    private String invoiceNumber;
    private double totalAmount;
    private Date orderDateTime;
    private Date paymentExpiredDateTime;
    private String note;

    private List<OrderProductResponse> result;
}
