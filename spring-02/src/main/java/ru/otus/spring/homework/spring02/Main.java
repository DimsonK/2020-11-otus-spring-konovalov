package ru.otus.spring.homework.spring02;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import ru.otus.spring.homework.spring02.service.StudentTestService;
import ru.otus.spring.homework.spring02.service.StudentTestServiceImpl;

@Configuration
@ComponentScan(basePackages = "ru.otus.spring.homework")
@PropertySource(value = {"classpath:application.properties"})
public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(Main.class);
        StudentTestService studentTestService = context.getBean(StudentTestServiceImpl.class);
        // Run test
        studentTestService.runTest();
    }
}
