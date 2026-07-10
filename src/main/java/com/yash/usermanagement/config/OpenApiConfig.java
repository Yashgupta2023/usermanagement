package com.yash.usermanagement.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI userManagementAPI() {

        return new OpenAPI()

                .info(new Info()

                        .title("User Management API")

                        .description("Spring Boot REST API with JWT Authentication")

                        .version("1.0")

                        .contact(new Contact()

                                .name("Yash Gupta")

                                .email("yash@example.com")
                        )
                )

                .externalDocs(new ExternalDocumentation()

                        .description("Project Documentation")

                        .url("https://springdoc.org"));
    }
}