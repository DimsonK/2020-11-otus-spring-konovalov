package ru.otus.spring.homework.spring04.service;

import ru.otus.spring.homework.spring04.domain.Person;

public interface StudentTestService {

    Person register();

    Person runTest(Person person);

    void printTestResult(Person person);

}
