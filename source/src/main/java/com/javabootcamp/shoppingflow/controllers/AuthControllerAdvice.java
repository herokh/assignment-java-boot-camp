package com.javabootcamp.shoppingflow.controllers;

import com.javabootcamp.shoppingflow.exceptions.AuthFailureException;
import com.javabootcamp.shoppingflow.views.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AuthControllerAdvice {

    @ExceptionHandler(AuthFailureException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse authFailure(AuthFailureException e) {
        return new ErrorResponse(e.getMessage());
    }
}
