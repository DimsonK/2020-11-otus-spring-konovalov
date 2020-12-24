package ru.otus.spring.homework.spring05.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Book class test")
class BookTest {

    @DisplayName("class create is correct")
    @Test
    void correctClassCreate() {
        Book book = new Book(
                10, "BookName",
                new Author(10, "author"),
                new Genre(10, "genre"));

        assertThat(book).isNotNull().isInstanceOf(Book.class);
        assertEquals(10, book.getId());
        assertEquals("BookName", book.getName());
        assertThat(book.getAuthor()).isNotNull().isInstanceOf(Author.class);
        assertThat(book.getGenre()).isNotNull().isInstanceOf(Genre.class);
    }
}