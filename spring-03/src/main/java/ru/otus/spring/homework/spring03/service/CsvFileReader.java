package ru.otus.spring.homework.spring03.service;

import ru.otus.spring.homework.spring03.domain.Question;

import java.util.List;

public interface CsvFileReader {

    List<Question> getQuestionList();

}
