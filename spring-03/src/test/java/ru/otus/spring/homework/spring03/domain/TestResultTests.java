package ru.otus.spring.homework.spring03.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("TestResult class test")
class TestResultTests {

    @DisplayName("class create is correct")
    @Test
    void correctClassCreate() {
        TestResult result = new TestResult("25", "35", true);

        assertEquals("25", result.getQuestionId());
        assertEquals("35", result.getAnswerId());
        assertTrue(result.isCorrect());
    }
}
