package ru.otus.spring.homework.spring03.domain;

public class TestResult {
    private final String questionId;
    private final String answerId;
    private final boolean isCorrect;

    public TestResult(String questionId, String answerId, boolean isCorrect) {
        this.questionId = questionId;
        this.answerId = answerId;
        this.isCorrect = isCorrect;
    }

    public String getQuestionId() {
        return questionId;
    }

    public String getAnswerId() {
        return answerId;
    }

    public boolean isCorrect() {
        return isCorrect;
    }

    @Override
    public String toString() {
        return "TestResult{" +
                "questionId='" + questionId + '\'' +
                ", answerId='" + answerId + '\'' +
                ", isCorrect=" + isCorrect +
                '}';
    }
}
