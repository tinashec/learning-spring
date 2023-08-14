package com.tuck.shop.utils;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * @author Tinashe on 14/8/2023
 */
@Configuration
@Getter @Setter
public class CorsProp {
    @Value("${cors.allowed-origins}")
    private String allowedOrigins;

    @Value("${cors.allowed-methods}")
    private String[] allowedMethods;

    @Value("${cors.allowed-headers}")
    private String allowedHeaders;
}
