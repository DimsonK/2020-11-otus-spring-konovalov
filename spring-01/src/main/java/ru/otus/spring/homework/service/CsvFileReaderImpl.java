package ru.otus.spring.homework.service;

import ru.otus.spring.homework.domain.Answer;
import ru.otus.spring.homework.domain.Question;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CsvFileReaderImpl implements CsvFileReader {
    private final String questionsFileName;
    private final String answersFileName;

    public CsvFileReaderImpl(String questionsFileName, String answersFileName) {
        this.questionsFileName = questionsFileName;
        this.answersFileName = answersFileName;
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
                Objects.requireNonNull(this.getClass().getClassLoader().getResourceAsStream(questionsFileName))))) {
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
            System.out.println(e.getMessage());
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
                Objects.requireNonNull(this.getClass().getClassLoader().getResourceAsStream(answersFileName))))) {
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
            System.out.println(e.getMessage());
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
}
