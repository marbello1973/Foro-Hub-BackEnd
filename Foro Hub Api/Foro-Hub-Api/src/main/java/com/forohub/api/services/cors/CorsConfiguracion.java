package com.forohub.api.services.cors;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfiguracion implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry){
        WebMvcConfigurer.super.addCorsMappings(registry);
    }
    public void addCorsConfiguracion(CorsRegistry registry){
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:8080")
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS", "HEAD", "TRACE", "CONNECT" )
                .allowedHeaders("*")
                .allowCredentials(true);
    }
}
