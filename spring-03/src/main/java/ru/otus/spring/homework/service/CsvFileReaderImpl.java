package ru.otus.spring.homework.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.otus.spring.homework.config.AppProp;
import ru.otus.spring.homework.domain.Answer;
import ru.otus.spring.homework.domain.Question;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class CsvFileReaderImpl implements CsvFileReader {
    private static final Logger log = LoggerFactory.getLogger(CsvFileReaderImpl.class);

    private final AppProp prop;
    private final LocaleService loc;

    public CsvFileReaderImpl(AppProp prop, LocaleServiceImpl loc) {
        this.prop = prop;
        this.loc = loc;
    }

    /**
     * Метод формирует массив вопросов с соответствующими им ответами
     * @return Questions with answers list
     */
    public List<Question> getQuestionList() {
        List<Question> questionList = readQuestionsFromCSV();
        questionList.forEach(question -> {
            List<Answer> answerList = readAnswersFromCSV(question);
            question.setAnswerList(answerList);
        });
        return questionList;
    }

    /**
     * Метод читает файл с вопросами
     * @return список вопросов
     */
    private List<Question> readQuestionsFromCSV() {
        List<Question> questions = null;
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(
                Objects.requireNonNull(this.getClass().getClassLoader().getResourceAsStream(getQuestionFileName()))))) {
            // инициализация списка
            questions = new ArrayList<>();
            // чтение первой строки
            String line = reader.readLine();
            // последовательное чтение строк из csv
            while (line != null) {
                String[] attr = line.split(",");
                Question question = createQuestion(attr);
                questions.add(question);
                line = reader.readLine();
            }
        } catch (IOException e) {
            log.error(e.getMessage());
        }
        return questions;
    }

    /**
     * Метод читает файл с ответами на определенный в параметрах вопрос
     * @param question - вопрос
     * @return список ответов на вопрос
     */
    private List<Answer> readAnswersFromCSV(Question question) {
        List<Answer> answers = null;
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(
                Objects.requireNonNull(this.getClass().getClassLoader().getResourceAsStream(getAnswersFileName()))))) {
            // инициализация списка
            answers = new ArrayList<>();
            // чтение первой строки
            String line = reader.readLine();
            // последовательное чтение строк из csv
            while (line != null) {
                String[] attr = line.split(",");
                // В список добавляются только ответы на вопрос в параметре метода
                if (attr[1].endsWith(question.getId())) {
                    Answer answer = createAnswer(attr);
                    answers.add(answer);
                }
                line = reader.readLine();
            }
        } catch (IOException e) {
            log.error(e.getMessage());
        }
        return answers;
    }

    /**
     * Маппинг полей вопроса
     * @param data поля вопроса: 0 - id, 1 - name
     * @return новый экземпляр вопроса
     */
    private Question createQuestion(String[] data) {
        String id = data[0];
        String name = data[1];

        return new Question(id, name);
    }

    /**
     * Маппинг полей ответа
     * @param data поля вопроса: 0 - id, 2 - name, 3 - "true"=верный ответ
     * @return новый экземпляр вопроса
     */
    private Answer createAnswer(String[] data) {
        String id = data[0];
        String name = data[2];
        boolean isCorrect = data[3].equals("true");

        return new Answer(id, name, isCorrect);
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
