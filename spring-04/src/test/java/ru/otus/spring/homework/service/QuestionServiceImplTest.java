package ru.otus.spring.homework.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.otus.spring.homework.config.AppProp;
import ru.otus.spring.homework.config.AppProp.TestResource;
import ru.otus.spring.homework.domain.Answer;
import ru.otus.spring.homework.domain.Question;

import java.util.List;
import java.util.Locale;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("QuestionServiceImplTest class test")
@SpringBootTest
class QuestionServiceImplTest {

    @MockBean AppProp appProp;
    @MockBean LocaleService localeService;
    @MockBean ResourceReader resourceReader;
    @Autowired QuestionServiceImpl questionService;

    @DisplayName("корректно создает объект вопросов с ответами")
    @Test
    void getQuestionList() {

        List<String[]> questions = List.of(
                new String[]{"1", "какого цвета флаг СССР"},
                new String[]{"2", "где находится северный полюс"});
        List<String[]> answers = List.of(
                new String[]{"1", "1", "голубой", "false"},
                new String[]{"2", "1", "красный", "true"},
                new String[]{"4", "2", "Юг", "false"},
                new String[]{"5", "2", "Восток", "false"});
        var resource = new TestResource();
        resource.setQuestions("tests/questions_ru_RU.csv");
        resource.setAnswers("tests/answers_ru_RU.csv");
        var store = Map.of("ru_RU", resource);
        var resQuestion = new Question("2", "где находится северный полюс");
        var resAnswers = List.of(
                new Answer("4", "Юг", false),
                new Answer("5", "Восток", false));
        resQuestion.setAnswerList(resAnswers);

        Mockito.when(appProp.getPassPercent()).thenReturn(20);
        Mockito.when(appProp.getStore()).thenReturn(store);
        Mockito.when(localeService.getLocale()).thenReturn(new Locale("ru", "RU"));
        Mockito.when(resourceReader.getResource("tests/questions_ru_RU.csv")).thenReturn(questions);
        Mockito.when(resourceReader.getResource("tests/answers_ru_RU.csv")).thenReturn(answers);

        List<Question> questionList = questionService.getQuestionList();

        assertThat(questionList).isNotNull().hasSize(2);
        assertThat(resQuestion).usingRecursiveComparison().isEqualTo(questionList.get(1));
    }
}