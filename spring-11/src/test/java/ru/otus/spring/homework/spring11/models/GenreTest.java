package ru.otus.spring.homework.spring11.models;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.otus.spring.homework.spring11.models.entity.Genre;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Genre class test")
class GenreTest {

    @DisplayName("class create is correct")
    @Test
    void correctClassCreate() {
        Genre genre = new Genre("Detective");
        assertEquals("Detective", genre.getName());
    }
}