package com.tuck.shop.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

/**
 * @author Tinashe on 27/8/2023
 * this configuration disables CORS on Spring security. CORS configs are in ../utils/CorsConfig. The
 * application.properties file has the actual values read. See:
 * <a href="https://stackoverflow.com/questions/71173132/cors-errors-using-spring-boot-spring-security-and-react">resolve cors error spring security</a>
 */
@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
        httpSecurity.cors(AbstractHttpConfigurer::disable);
        return httpSecurity.build();
    }
}
