package ru.otus.spring.homework.spring04.domain;

public class Answer {
    private final String id;
    private final String name;
    private final boolean isCorrect;

    public Answer(String id, String name, boolean isCorrect) {
        this.id = id;
        this.name = name;
        this.isCorrect = isCorrect;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public boolean isCorrect() {
        return isCorrect;
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
