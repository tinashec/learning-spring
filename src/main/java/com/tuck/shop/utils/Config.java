package com.tuck.shop.utils;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author Tinashe on 14/8/2023
 */
@Configuration
@EnableWebMvc
public class Config implements WebMvcConfigurer {

    private final CorsProp corsProp;

    @Autowired
    public Config(CorsProp corsProp) {
        this.corsProp = corsProp;
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        WebMvcConfigurer.super.addCorsMappings(registry);
        registry.addMapping("/**").
                allowedOrigins(corsProp.getAllowedOrigins()).
                allowedMethods(corsProp.getAllowedMethods()).
                allowedHeaders(corsProp.getAllowedHeaders());
    }

    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }
}
