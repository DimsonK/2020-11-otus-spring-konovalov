package ru.otus.spring.homework.springproject.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SwaggerConfig {
    private final AppProperties properties;

    public SwaggerConfig(AppProperties properties) {
        this.properties = properties;
    }

    @Bean
    public GroupedOpenApi authApi() {
        return GroupedOpenApi.builder()
                .group("Authorisation API")
                .pathsToExclude("/auth")
                .build();
    }

    @Bean
    public GroupedOpenApi authorApi() {
        return GroupedOpenApi.builder()
                .group("Authors API")
                .pathsToExclude("/api/author")
                .build();
    }

    @Bean
    public GroupedOpenApi bookApi() {
        return GroupedOpenApi.builder()
                .group("Books API")
                .pathsToExclude("/api/book")
                .build();
    }

    @Bean
    public GroupedOpenApi commentApi() {
        return GroupedOpenApi.builder()
                .group("Comments API")
                .pathsToExclude("/api/comment")
                .build();
    }

    @Bean
    public GroupedOpenApi genreApi() {
        return GroupedOpenApi.builder()
                .group("Genre API")
                .pathsToExclude("/api/genre")
                .build();
    }

    @Bean
    public OpenAPI customOpenApi() {
        return new OpenAPI().info(new Info().title("Bookworm Application API")
                .version(properties.getAppVersion())
                .description(properties.getAppDescription())
                .license(new License().name("Apache 2.0").url("http://springdoc.org/"))
                .contact(new Contact().name("KonovalovDV").email("dimsonk@gmail.com"))).servers(List.of(
                new Server().url("http://localhost:8080").description("Dev server")));
    }

}
