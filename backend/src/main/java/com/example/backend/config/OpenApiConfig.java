package com.example.backend.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc
@OpenAPIDefinition(info = @Info(title = "Backend API", version = "v1", description = "API Documentation"))
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .addSecurityItem(new SecurityRequirement().addList("jwtAuth"))
                .components(new Components()
                        .addSecuritySchemes("jwtAuth", new SecurityScheme()
                                .type(SecurityScheme.Type.HTTP)
                                .description("JWT Authentication")
                                .scheme("bearer")
                                .bearerFormat("JWT")));
    }
}
