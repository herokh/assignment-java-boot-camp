package com.javabootcamp.shoppingflow.exceptions;

public class AuthFailureException extends RuntimeException {
    public AuthFailureException(String message) {
        super(message);
    }
}
