package com.convertPdfA.convertPdfA.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class ConvertPdfaConfiguration implements WebMvcConfigurer{
	
	@Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
            .allowedOrigins("*") // Permitir solicitações somente do seu front-end
            .allowedMethods("GET", "POST") // Permitir métodos GET e POST
            .allowedHeaders("*"); // Permitir todos os cabeçalhos
    }

}
