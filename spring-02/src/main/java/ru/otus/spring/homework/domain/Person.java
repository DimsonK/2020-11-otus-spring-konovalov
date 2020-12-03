package ru.otus.spring.homework.domain;

import java.util.ArrayList;
import java.util.List;

public class Person {
    private final String firstName;
    private final String lastName;
    private List<TestResult> testResultList;

    public Person(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.testResultList = new ArrayList<>();
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public List<TestResult> getTestResultList() {
        return testResultList;
    }

    public void setTestResultList(List<TestResult> testResultResultList) {
        this.testResultList = testResultResultList;
    }

    public void addTestResult(TestResult testResult) {
        this.testResultList.add(testResult);
    }

    @Override
    public String toString() {
        return "Person{" +
                "firstName='" + firstName + '\'' +
                ", lastName=" + lastName +
                ", testResultList=" + testResultList +
                '}';
    }
}
