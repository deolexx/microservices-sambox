package com.deo.microservices.currencyexchangeservice.config;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class WevMvcConfig implements WebMvcConfigurer {

    @Value("${gateway.uri}")
    String origin;

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedMethods("GET")
                .allowedOrigins(origin)
                .allowedHeaders("*")
                .allowCredentials(false)
                .maxAge(-1);
    }

}
