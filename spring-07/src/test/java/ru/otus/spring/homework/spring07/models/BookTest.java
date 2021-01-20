package ru.otus.spring.homework.spring07.models;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Book class test")
class BookTest {

    @DisplayName("class create is correct")
    @Test
    void correctClassCreate() {

        var author = new Author(10, "author");
        var genre = new Genre(10, "genre");
        var book = new Book(10, "BookName", author, List.of(genre), null);
        var comment = new Comment(100, "misha", "25.06.2020", "Комментарий", book);

        book.setComments(List.of(comment));

        assertThat(book).isNotNull().isInstanceOf(Book.class);
        assertEquals(10, book.getId());
        assertEquals("BookName", book.getName());
        assertThat(book.getAuthor()).isNotNull().isInstanceOf(Author.class);
        assertThat(book.getGenres()).isNotNull();
        assertThat(book.getComments()).isNotNull();
    }
}