package ru.otus.spring.homework.spring02.service;

import ru.otus.spring.homework.spring02.domain.Question;

import java.util.List;

public interface CsvFileReader {

    List<Question> getQuestionList();

}
