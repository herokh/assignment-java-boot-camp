package com.javabootcamp.shoppingflow.controllers;

import com.javabootcamp.shoppingflow.exceptions.AuthFailureException;
import com.javabootcamp.shoppingflow.exceptions.BasketEmptyException;
import com.javabootcamp.shoppingflow.exceptions.ProductNotFoundException;
import com.javabootcamp.shoppingflow.views.common.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class BasketsControllerAdvice {

    @ExceptionHandler(ProductNotFoundException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse productNotFound(ProductNotFoundException e) {
        return new ErrorResponse(e.getMessage());
    }

    @ExceptionHandler(BasketEmptyException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse basketEmpty(BasketEmptyException e) {
        return new ErrorResponse(e.getMessage());
    }
}
