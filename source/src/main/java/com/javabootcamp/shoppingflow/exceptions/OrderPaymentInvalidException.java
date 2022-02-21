package com.javabootcamp.shoppingflow.exceptions;

public class OrderPaymentInvalidException extends RuntimeException {
    public OrderPaymentInvalidException(String message) {
        super(message);
    }
}
