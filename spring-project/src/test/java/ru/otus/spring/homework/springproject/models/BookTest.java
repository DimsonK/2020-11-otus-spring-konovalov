package ru.otus.spring.homework.springproject.models;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.otus.spring.homework.springproject.models.entity.Author;
import ru.otus.spring.homework.springproject.models.entity.Book;
import ru.otus.spring.homework.springproject.models.entity.Genre;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Book class test")
class BookTest {

    @DisplayName("class create is correct")
    @Test
    void correctClassCreate() {

        var author = new Author(10L, "author");
        var genre = new Genre(10L, "genre");
        var book = new Book(10L, "BookName", 12, 2, author, List.of(genre));

        assertThat(book).isNotNull().isInstanceOf(Book.class);
        assertEquals(10L, book.getId());
        assertEquals("BookName", book.getName());
        assertThat(book.getAuthor()).isNotNull().isInstanceOf(Author.class);
        assertThat(book.getGenres()).isNotNull();
    }
}
