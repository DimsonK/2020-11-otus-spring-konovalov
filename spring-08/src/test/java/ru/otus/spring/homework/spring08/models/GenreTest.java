package ru.otus.spring.homework.spring08.models;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

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