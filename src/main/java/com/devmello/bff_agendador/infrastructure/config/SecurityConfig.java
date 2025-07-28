package com.devmello.bff_agendador.infrastructure.config;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import lombok.RequiredArgsConstructor;

@SecurityScheme(
        name = SecurityConfig.SECURITY_SCHEME,
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        scheme = "bearer"
)
@RequiredArgsConstructor
public class SecurityConfig {
    public static final String SECURITY_SCHEME = "bearerAuth";
}
