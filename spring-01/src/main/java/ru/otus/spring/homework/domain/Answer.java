package ru.otus.spring.homework.domain;

public class Answer {
    private String id;
    private String name;
    private boolean isCorrect;

    public Answer() {
    }

    public Answer(String id, String name, boolean isCorrect) {
        this.id = id;
        this.name = name;
        this.isCorrect = isCorrect;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isCorrect() {
        return isCorrect;
    }

    public void setCorrect(boolean correct) {
        isCorrect = correct;
    }

    @Override
    public String toString() {
        return "Answer{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", isCorrect=" + isCorrect +
                '}';
    }
}
