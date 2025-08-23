package com.lifeassist.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.Components;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
            .info(new Info()
                .title("LifeAssist API")
                .version("1.0")
                .description("API documentation for LifeAssist - Elderly Care Assistance and Healthcare Support Platform")
                .contact(new Contact()
                    .name("Hasnat Alam")
                    .email("hasnatalamofficial@gmail.com")
                )
                .license(new License()
                    .name("Apache 2.0")
                    .url("https://www.apache.org/licenses/LICENSE-2.0"))
            )
            // 🔑 Add global JWT security requirement
            .addSecurityItem(new SecurityRequirement().addList("jwtAuth"))
            .components(new Components()
                .addSecuritySchemes("jwtAuth",
                    new SecurityScheme()
                        .name("jwtAuth")
                        .type(SecurityScheme.Type.HTTP)
                        .scheme("bearer")   // protocol = bearer
                        .bearerFormat("JWT") // format = JWT
                )
            );
    }
}
