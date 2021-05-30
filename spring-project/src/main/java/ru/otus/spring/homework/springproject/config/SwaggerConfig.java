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
            .pathsToMatch("/auth")
            .build();
    }

    @Bean
    public GroupedOpenApi authorApi() {
        return GroupedOpenApi.builder()
            .group("Authors API")
            .pathsToMatch("/api/author")
            .build();
    }

    @Bean
    public GroupedOpenApi basketApi() {
        return GroupedOpenApi.builder()
            .group("Basket API")
            .pathsToMatch("/api/basket")
            .build();
    }

    @Bean
    public GroupedOpenApi bookApi() {
        return GroupedOpenApi.builder()
            .group("Books API")
            .pathsToMatch("/api/book")
            .build();
    }

    @Bean
    public GroupedOpenApi commentApi() {
        return GroupedOpenApi.builder()
            .group("Comments API")
            .pathsToMatch("/api/comment")
            .build();
    }

    @Bean
    public GroupedOpenApi genreApi() {
        return GroupedOpenApi.builder()
            .group("Genre API")
            .pathsToMatch("/api/genre")
            .build();
    }

    @Bean
    public GroupedOpenApi instanceApi() {
        return GroupedOpenApi.builder()
            .group("Instance API")
            .pathsToMatch("/api/instance")
            .build();
    }

    @Bean
    public GroupedOpenApi issueApi() {
        return GroupedOpenApi.builder()
            .group("Issue API")
            .pathsToMatch("/api/issue")
            .build();
    }

//    @Bean
//    public GroupedOpenApi issueInstanceApi() {
//        return GroupedOpenApi.builder()
//            .group("Issue Instance API")
//            .pathsToMatch("/api/issue_instance")
//            .build();
//    }

    @Bean
    public GroupedOpenApi orderApi() {
        return GroupedOpenApi.builder()
            .group("Order API")
            .pathsToMatch("/api/order")
            .build();
    }

    @Bean
    public GroupedOpenApi roleApi() {
        return GroupedOpenApi.builder()
            .group("Role API")
            .pathsToMatch("/api/role")
            .build();
    }

    @Bean
    public GroupedOpenApi userApi() {
        return GroupedOpenApi.builder()
            .group("Issue Instance API")
            .pathsToMatch("/api/user")
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
