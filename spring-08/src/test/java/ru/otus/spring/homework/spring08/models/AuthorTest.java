package ru.otus.spring.homework.spring08.models;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Author class test")
class AuthorTest {

    @DisplayName("class create is correct")
    @Test
    void correctClassCreate() {
        Author author = new Author("Ivan");
        assertEquals("Ivan", author.getName());
    }
}