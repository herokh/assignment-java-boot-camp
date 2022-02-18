package com.javabootcamp.shoppingflow.utils;

import com.javabootcamp.shoppingflow.models.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.DefaultClaims;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtUtil {

    private static final String SECRET_KEY = "SECRET";

    public static String generateToken(User user, Date expirationDate) {
        Map<String, Object> claims = new HashMap<>();
        return doGenerateToken(claims, user.getUsername(), expirationDate);
    }

    public static String getUsernameFromToken(String jwtToken) {
        try {
            Claims claims;
            Jwt jwtModel = Jwts.parser()
                    .setSigningKey(SECRET_KEY)
                    .parse(jwtToken);
            claims = (Claims) jwtModel.getBody();
            return claims.getSubject();
        }
        catch (Exception e) {
            throw new RuntimeException("Invalid credential");
        }
    }

    private static String doGenerateToken(Map<String, Object> claims, String subject, Date expirationDate) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }
}
