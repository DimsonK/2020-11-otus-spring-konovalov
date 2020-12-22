package ru.otus.spring.homework.spring02.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Person class test")
class AnswerTest {

    @DisplayName("class create is correct")
    @Test
    void correctClassCreate() {
        Answer answer = new Answer("2", "answer body", false);

        assertEquals("2", answer.getId());
        assertEquals("answer body", answer.getName());
        assertFalse(answer.isCorrect());
    }
}
