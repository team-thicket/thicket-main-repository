package com.example.thicketmember.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition(
        info = @Info(title = "Member API 명세서",
               description = "멤버 어플 서비스 API 명세서",
                   version = "v1"))
@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI openAPI(){
        SecurityScheme securityScheme = new SecurityScheme()
            .type(SecurityScheme.Type.APIKEY)
            .in(SecurityScheme.In.HEADER)
            .name("Email");

        return new OpenAPI()
            .components(new Components()
            .addSecuritySchemes("ApiKey", securityScheme))
            .addSecurityItem(new SecurityRequirement().addList("ApiKey"));
    }
}