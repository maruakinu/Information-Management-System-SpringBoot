package com.springboot.crud.mysqlspring.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JwtUtilsConfiguration {

    @Bean
    public JwtUtils getJwtUtils(
            @Value("${login.auth.token.sign-key}") String signKey,
            @Value("${login.auth.token.valid-time}") Long validTime
    ) throws Exception {
        if (signKey.length() < 32) {
            throw new Exception("signKey must have length at least 32");
        }
        return new JwtUtils(signKey, validTime);
    }

}
