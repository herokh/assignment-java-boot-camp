package com.javabootcamp.shoppingflow.views.auth;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

public class AuthResponse {
    private @Getter @Setter String token;
    private @Getter @Setter Date expiredDate;
}
