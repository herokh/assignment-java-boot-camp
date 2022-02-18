package com.javabootcamp.shoppingflow.controllers;

import com.javabootcamp.shoppingflow.views.auth.AuthRequest;
import com.javabootcamp.shoppingflow.views.auth.AuthResponse;
import com.javabootcamp.shoppingflow.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping(value = "/signin",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    public AuthResponse signIn(@RequestBody AuthRequest authRequest) {
        AuthResponse authResponse = authService.signIn(authRequest);
        return authResponse;
    }

}
