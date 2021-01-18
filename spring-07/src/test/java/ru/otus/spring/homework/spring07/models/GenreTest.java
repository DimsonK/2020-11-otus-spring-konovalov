package ru.otus.spring.homework.spring07.models;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Genre class test")
class GenreTest {

    @DisplayName("class create is correct")
    @Test
    void correctClassCreate() {
        Genre genre = new Genre(10, "Detective");
        assertEquals(10, genre.getId());
        assertEquals("Detective", genre.getName());
    }
}