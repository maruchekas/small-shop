package com.maruchek.smallshop.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(info = @Info(title = "Small computer shop", description = "Small computer shop",
        version = "1.0"))
public class SwaggerConfig {
}