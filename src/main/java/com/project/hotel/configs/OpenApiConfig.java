package com.project.hotel.configs;

import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;



@OpenAPIDefinition(
        info = @Info(
                title = "Hotel manager api in Java Spring boot",
                version = "1.0.0",
                description = "Website đặt phòng để thực hành Java Spring boot",
        contact = @Contact(
                name = "Hồ Minh Trung",
                url = "https://github.com/bykytadev",
                email = "hmtrung1510@gmail.com")
        ),
        security = @SecurityRequirement(name = "bearer-key"),
        servers = {
            @Server(url = "http://localhost:8080", description = "Local Development Server"),
            @Server(url = "http://171.239.86.22:8080", description = "Production Server"),
        }
)

@SecurityScheme(
        name = "bearer-key", 
        type = SecuritySchemeType.HTTP,
        scheme = "bearer",
        bearerFormat = "JWT",
        in = SecuritySchemeIn.HEADER
)

@Configuration
public class OpenApiConfig {

}