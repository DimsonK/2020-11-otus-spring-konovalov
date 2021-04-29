package ru.otus.spring.homework.springproject.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import ru.otus.spring.homework.springproject.models.entity.AuditorAwareImpl;

@Configuration
@EnableConfigurationProperties({
    AppProperties.class
})
@EnableTransactionManagement
@EnableJpaRepositories("ru.otus.spring.homework.springproject.repositories")
@EnableJpaAuditing(auditorAwareRef = "auditorProvider")
public class AppConfig {

    @Bean
    public AuditorAware<String> auditorProvider() {
        return new AuditorAwareImpl();
    }

}
