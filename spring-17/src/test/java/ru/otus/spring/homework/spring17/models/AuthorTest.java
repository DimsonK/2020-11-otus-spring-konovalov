package ru.otus.spring.homework.spring17.models;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.otus.spring.homework.spring17.models.entity.Author;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Author class test")
class AuthorTest {

    @DisplayName("class create is correct")
    @Test
    void correctClassCreate() {
        Author author = new Author(10, "Ivan");
        assertEquals(10, author.getId());
        assertEquals("Ivan", author.getName());
    }
}