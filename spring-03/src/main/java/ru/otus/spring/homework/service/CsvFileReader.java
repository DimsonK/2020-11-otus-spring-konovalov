package ru.otus.spring.homework.service;

import ru.otus.spring.homework.domain.Question;

import java.util.List;

public interface CsvFileReader {

    List<Question> getQuestionList();

}
