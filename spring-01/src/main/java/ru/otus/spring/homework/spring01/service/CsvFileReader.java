package ru.otus.spring.homework.spring01.service;

import ru.otus.spring.homework.spring01.domain.Question;

import java.util.List;

public interface CsvFileReader {

    List<Question> getQuestionList();

}
