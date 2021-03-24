package ru.otus.spring.homework.spring15.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.otus.spring.homework.spring15.domain.Good;

import java.math.BigDecimal;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.ForkJoinPool;

@Configuration
public class AppConfig {

    public static final List<Good> GOOD_LIST = List.of(
            new Good("Товар 1", new BigDecimal("100.00"), 0.200F),
            new Good("Товар 2", new BigDecimal("50.00"), 1.200F),
            new Good("Товар 3", new BigDecimal("120.00"), 2.200F),
            new Good("Товар 4", new BigDecimal("135.00"), 4.200F),
            new Good("Товар 5", new BigDecimal("1200.00"), 0.500F),
            new Good("Товар 6", new BigDecimal("2300.00"), 0.600F),
            new Good("Товар 7", new BigDecimal("5000.00"), 1.240F),
            new Good("Товар 8", new BigDecimal("1358.00"), 5.200F),
            new Good("Товар 9", new BigDecimal("5869.00"), 4.203F),
            new Good("Товар 10", new BigDecimal("2130.00"), 2.250F));

    @Bean("threadPoolTaskExecutor")
    public Executor getAsyncExecutor() {
        return new ForkJoinPool();
    }

}
