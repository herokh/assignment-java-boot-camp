package com.javabootcamp.shoppingflow;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

public class ShoppingFlowTestUtil {

    static <T extends Object> HttpEntity<T> createRequestHttpEntity(String userToken, T body) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add(HttpHeaders.AUTHORIZATION, String.format("Bearer %s", userToken));
        var entity = new HttpEntity<T>(body, headers);
        return entity;
    }

}
