package com.javabootcamp.shoppingflow.views.auth;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class AuthResponse {
    private String token;
    private Date expiredDate;
}
