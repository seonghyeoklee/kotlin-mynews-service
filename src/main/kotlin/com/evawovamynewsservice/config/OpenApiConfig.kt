package com.evawovamynewsservice.config

import io.swagger.v3.oas.models.Components
import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Info
import io.swagger.v3.oas.models.security.SecurityRequirement
import io.swagger.v3.oas.models.security.SecurityScheme
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class OpenApiConfig {
    @Bean
    fun openAPI(): OpenAPI =
        OpenAPI()
            .info(getInfo())
            .components(getComponents())
            .addSecurityItem(getSecurityItem())

    private fun getSecurityItem(): SecurityRequirement? = SecurityRequirement().addList("Json Web Token")

    private fun getComponents(): Components =
        Components().addSecuritySchemes(
            "Json Web Token",
            SecurityScheme()
                .type(SecurityScheme.Type.HTTP)
                .scheme("bearer")
                .bearerFormat("JWT"),
        )

    private fun getInfo(): Info =
        Info()
            .title("News Service")
            .description("News Service API")
            .version("v1")
}
