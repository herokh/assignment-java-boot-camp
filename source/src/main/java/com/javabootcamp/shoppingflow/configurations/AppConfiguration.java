package com.javabootcamp.shoppingflow.configurations;

import com.javabootcamp.shoppingflow.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;

@Configuration
public class AppConfiguration {

    @Bean
    @Scope(value = "request",  proxyMode = ScopedProxyMode.TARGET_CLASS)
    public ApplicationContext myApplicationContext() {
        return new ApplicationContext();
    }

}
