package com.javabootcamp.shoppingflow.controllers;

import com.javabootcamp.shoppingflow.exceptions.OrderNotFoundException;
import com.javabootcamp.shoppingflow.exceptions.OrderPaymentInvalidException;
import com.javabootcamp.shoppingflow.exceptions.ProductNotFoundException;
import com.javabootcamp.shoppingflow.views.common.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class OrdersControllerAdvice {

    @ExceptionHandler(OrderNotFoundException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse orderNotFound(OrderNotFoundException e) {
        return new ErrorResponse(e.getMessage());
    }

    @ExceptionHandler(ProductNotFoundException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse productNotFound(ProductNotFoundException e) {
        return new ErrorResponse(e.getMessage());
    }

    @ExceptionHandler(OrderPaymentInvalidException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse orderPaymentInvalid(OrderPaymentInvalidException e) {
        return new ErrorResponse(e.getMessage());
    }
}
