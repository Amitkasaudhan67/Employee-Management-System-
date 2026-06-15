package com.ems.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI employeeManagementOpenApi() {
        return new OpenAPI()
                .info(new Info()
                        .title("Employee Management System REST API")
                        .version("1.0.0")
                        .description("Spring Boot REST API for managing employees with JPA, Hibernate, MySQL, validation and exception handling.")
                        .contact(new Contact()
                                .name("Employee Management System")
                                .email("support@example.com")));
    }
}
