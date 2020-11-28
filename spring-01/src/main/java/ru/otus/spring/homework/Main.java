package ru.otus.spring.homework;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.otus.spring.homework.domain.Answer;
import ru.otus.spring.homework.domain.Question;
import ru.otus.spring.homework.service.CsvFileReader;

import java.util.List;

public class Main {

    public static void main(String[] args) {

        // Get context
        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext("/spring-context.xml");
        CsvFileReader csvFileReader = context.getBean(CsvFileReader.class);

        // Get questions from resources
        List<Question> questions = csvFileReader.getQuestionList();
        // Print result
        questions.forEach(question -> {
            System.out.println(question.getId() + ". " + question.getName());
            List<Answer> answers = question.getAnswerList();
            for (int i = 0; i < answers.size(); i++) {
                System.out.print("    " + (i + 1) + ". " + answers.get(i).getName());
                System.out.println(answers.get(i).isCorrect() ? " *" : "");
            }
        });

    }


}
