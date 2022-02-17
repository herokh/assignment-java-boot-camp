package com.javabootcamp.shoppingflow.utils;

import com.javabootcamp.shoppingflow.models.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtUtil {

    public static String generateToken(User user, Date expirationDate) {
        Map<String, Object> claims = new HashMap<>();
        return doGenerateToken(claims, user.getUsername(), expirationDate);
    }

    private static String doGenerateToken(Map<String, Object> claims, String subject, Date expirationDate) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS256, "SECRET")
                .compact();
    }
}
