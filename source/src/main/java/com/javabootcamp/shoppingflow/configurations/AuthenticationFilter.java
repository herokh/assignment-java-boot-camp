package com.javabootcamp.shoppingflow.configurations;

import com.javabootcamp.shoppingflow.ApplicationContext;
import com.javabootcamp.shoppingflow.repositories.UserRepository;
import com.javabootcamp.shoppingflow.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Configuration
public class AuthenticationFilter extends OncePerRequestFilter {

    private static final String AUTHORIZATION_HEADER = "Authorization";

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ApplicationContext applicationContext;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        try {
            String jwtToken = request.getHeader(AUTHORIZATION_HEADER).replace("Bearer ", "");
            var username = JwtUtil.getUsernameFromToken(jwtToken);
            var user = userRepository.findByUsername(username).orElseThrow(
                    () -> new RuntimeException("User not found."));
            applicationContext.setCurrentUserIfNotExists(user);
            filterChain.doFilter(request, response);
        }
        catch (Exception e) {
            response.sendError(HttpStatus.UNAUTHORIZED.value(), e.getMessage());
        }

    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        String path = request.getServletPath();
        return !path.startsWith("/api/secure/");
    }
}
