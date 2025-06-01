package com.devmello.bff_agendador.infrastructure.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DocConfig {
    @Bean
    public OpenAPI springDoc() {
        return new OpenAPI()
                .info(new Info().title("Agendador API")
                        .description("Aplicação spring para criar um agendador de tarefas")
                        .version("0.0.1"));
    }
}
