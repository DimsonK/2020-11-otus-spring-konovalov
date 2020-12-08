package ru.otus.spring.homework;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import ru.otus.spring.homework.config.AppProp;
import ru.otus.spring.homework.service.StudentTestServiceImpl;

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
