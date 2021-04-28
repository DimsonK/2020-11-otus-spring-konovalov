package ru.otus.spring.homework.springproject.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableConfigurationProperties({
    AppProperties.class
})
@EnableTransactionManagement
@EnableJpaRepositories("ru.otus.spring.homework.springproject.repositories")
@EnableJpaAuditing
public class AppConfig {
}
