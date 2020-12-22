package ru.otus.spring.homework.spring03;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import ru.otus.spring.homework.spring03.config.AppProp;
import ru.otus.spring.homework.spring03.service.StudentTestServiceImpl;

@SpringBootApplication
@EnableConfigurationProperties(AppProp.class)
public class Main {
    public static void main(String[] args) {
        var context = SpringApplication.run(Main.class, args);
        var studentTestService = context.getBean(StudentTestServiceImpl.class);
        // Run test
        studentTestService.runTest();
    }
}
