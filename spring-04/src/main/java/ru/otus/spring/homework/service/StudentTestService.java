package ru.otus.spring.homework.service;

import ru.otus.spring.homework.domain.Person;

public interface StudentTestService {

    Person register();

    Person runTest(Person person);

    void printTestResult(Person person);

}
