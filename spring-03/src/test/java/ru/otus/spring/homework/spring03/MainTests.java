package ru.otus.spring.homework.spring03;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNull;

@DisplayName("Main class test")
class MainTests {

    @DisplayName("First test")
    @Test
    void fun() {
        assertNull(null);
    }

}