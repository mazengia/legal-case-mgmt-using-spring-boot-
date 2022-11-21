package com.enatbanksc.casemanagementsystem.case_management.config;


import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(info = @Info(title = "Case Management System API",
        version = "0.0.1-SNAPSHOT",
        description = "Case Management System for Enat Banks S.C",
        contact = @Contact(email = "mbekana@enatbanksc.com", url = "case@management.com")

), security = {
        @SecurityRequirement(name = "bearerAuth")
},
        servers = {
                @Server(
                        url = "http://10.1.80.67:7070/",
                        description = "DEV Server"
                ),
                @Server(
                        url = "http://10.1.12.70:9000/",
                        description = "PROD Server"
                )})

@SecurityScheme(
        name = "bearerAuth",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        scheme = "bearer"
)
public class OpenApiConfig {
}
