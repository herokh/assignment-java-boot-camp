package com.javabootcamp.shoppingflow.mocks;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

public class ShoppingFlowHttpMock {

    public static <T extends Object> HttpEntity<T> createHttpEntityMock(String userToken, T body) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add(HttpHeaders.AUTHORIZATION, String.format("Bearer %s", userToken));
        var entity = new HttpEntity<T>(body, headers);
        return entity;
    }

}
