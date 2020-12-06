package ru.otus.spring.homework.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Question class test")
class QuestionTest {

    @DisplayName("class create is correct")
    @Test
    void correctClassCreate() {
        Question question = new Question("4", "question body");

        assertEquals("4", question.getId());
        assertEquals("question body", question.getName());
    }
}
