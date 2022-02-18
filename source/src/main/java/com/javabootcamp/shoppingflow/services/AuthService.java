package com.javabootcamp.shoppingflow.services;

import com.javabootcamp.shoppingflow.exceptions.AuthFailureException;
import com.javabootcamp.shoppingflow.utils.BCryptUtil;
import com.javabootcamp.shoppingflow.utils.JwtUtil;
import com.javabootcamp.shoppingflow.views.auth.AuthRequest;
import com.javabootcamp.shoppingflow.views.auth.AuthResponse;
import com.javabootcamp.shoppingflow.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class AuthService {

    private static final int JWT_TOKEN_VALIDITY =  60 * 60 * 1000;

    @Autowired
    private UserRepository userRepository;

    public AuthResponse signIn(AuthRequest authRequest) {
        var hasUser = userRepository.existsByUsername(authRequest.getUsername());
        if (hasUser){
            var user = userRepository.findByUsername(authRequest.getUsername());
            var isPasswordValid = BCryptUtil.validateHashString(authRequest.getPassword(), user.get().getPassword());
            if (isPasswordValid) {
                var userLoginResponse = new AuthResponse();
                userLoginResponse.setExpiredDate(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY));
                userLoginResponse
                        .setToken(JwtUtil.generateToken(user.get(),
                                userLoginResponse.getExpiredDate()));
                return userLoginResponse;
            }
            throw new AuthFailureException("Password is wrong.");
        }
        throw new AuthFailureException("User not found.");
    }
}
