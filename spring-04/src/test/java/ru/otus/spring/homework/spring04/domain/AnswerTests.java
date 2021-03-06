package ru.otus.spring.homework.spring04.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@DisplayName("Person class test")
class AnswerTests {

    @DisplayName("class create is correct")
    @Test
    void correctClassCreate() {
        Answer answer = new Answer("2", "answer body", false);

        assertEquals("2", answer.getId());
        assertEquals("answer body", answer.getName());
        assertFalse(answer.isCorrect());
    }
}
