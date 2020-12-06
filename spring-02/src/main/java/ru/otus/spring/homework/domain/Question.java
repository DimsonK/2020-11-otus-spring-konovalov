package ru.otus.spring.homework.domain;

import java.util.List;

public class Question {
    private final String id;
    private final String name;
    private List<Answer> answerList;

    public Question(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Answer> getAnswerList() {
        return answerList;
    }

    public void setAnswerList(List<Answer> answerList) {
        this.answerList = answerList;
    }

    @Override
    public String toString() {
        return "Question{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", answerList=" + answerList +
                '}';
    }
}
