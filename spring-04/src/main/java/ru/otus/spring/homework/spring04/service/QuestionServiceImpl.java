package ru.otus.spring.homework.spring04.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.otus.spring.homework.spring04.config.AppProp;
import ru.otus.spring.homework.spring04.domain.Answer;
import ru.otus.spring.homework.spring04.domain.Question;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class QuestionServiceImpl implements QuestionService {
    private static final Logger log = LoggerFactory.getLogger(QuestionServiceImpl.class);

    private final AppProp prop;
    private final LocaleService loc;
    private final ResourceReader rr;

    public QuestionServiceImpl(AppProp prop, LocaleService loc, ResourceReader rr) {
        this.prop = prop;
        this.loc = loc;
        this.rr = rr;
    }

    /**
     * Метод формирует массив вопросов с соответствующими им ответами
     * @return Questions with answers list
     */
    public List<Question> getQuestionList() {
        List<Question> questionList = getQuestions();
        questionList.forEach(question -> {
            List<Answer> answerList = getAnswers(question);
            question.setAnswerList(answerList);
        });
        return questionList;
    }

    /**
     * Метод получает ресурсы вопросов и строит из них список вопросов
     * @return список вопросов
     */
    private List<Question> getQuestions() {
        return rr.getResource(getQuestionFileName()).stream()
                .map(this::createQuestion)
                .collect(Collectors.toList());
    }

    /**
     * Метод получает ресурс с ответами на определенный в параметрах вопрос
     * @param question - вопрос
     * @return список ответов на вопрос
     */
    private List<Answer> getAnswers(Question question) {
        return rr.getResource(getAnswersFileName()).stream()
                .filter(line -> line[1].endsWith(question.getId()))
                .map(this::createAnswer)
                .collect(Collectors.toList());
    }

    /**
     * Маппинг полей вопроса
     * @param data поля вопроса: 0 - id, 1 - name
     * @return новый экземпляр вопроса
     */
    private Question createQuestion(String[] data) {
        return new Question(data[0], data[1]);
    }

    /**
     * Маппинг полей ответа
     * @param data поля вопроса: 0 - id, 2 - name, 3 - "true"=верный ответ
     * @return новый экземпляр вопроса
     */
    private Answer createAnswer(String[] data) {
        return new Answer(data[0], data[2], data[3].equals("true"));
    }

    /**
     * получаем имя файла вопросов
     * @return имя файла
     */
    private String getQuestionFileName() {
        return prop.getStore().get(loc.getLocale().toString()).getQuestions();
    }

    /**
     * получаем имя файла ответов
     * @return имя файла
     */
    private String getAnswersFileName() {
        return prop.getStore().get(loc.getLocale().toString()).getAnswers();
    }

}
