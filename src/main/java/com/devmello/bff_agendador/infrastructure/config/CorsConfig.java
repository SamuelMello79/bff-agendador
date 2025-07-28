package com.devmello.bff_agendador.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig {

    @Bean
    public WebMvcConfigurer corsConfig() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**") // Para quais rotas permite
                        .allowedOrigins("http://localhost:4200") // Origins aceitas
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "PATCH") // Métodos permitidos
                        .allowedHeaders("*") // Define quais headers vai aceitar
                        .allowCredentials(true) // Define se vai permitir receber dados no header
                        .maxAge(360); // Define o tempo que o navegador vai cachear essa requisição
            }
        };
    }
}
