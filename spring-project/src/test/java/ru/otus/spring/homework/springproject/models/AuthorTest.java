package ru.otus.spring.homework.springproject.models;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.otus.spring.homework.springproject.models.entity.Author;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Author class test")
class AuthorTest {

    @DisplayName("class create is correct")
    @Test
    void correctClassCreate() {
        Author author = new Author(10L, "Ivan");
        assertEquals(10L, author.getId());
        assertEquals("Ivan", author.getName());
    }
}
