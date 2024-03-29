package ru.otus.spring.homework.spring11.models;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.otus.spring.homework.spring11.models.entity.Author;
import ru.otus.spring.homework.spring11.models.entity.Book;
import ru.otus.spring.homework.spring11.models.entity.Comment;
import ru.otus.spring.homework.spring11.models.entity.Genre;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Book class test")
class BookTest {

    @DisplayName("class create is correct")
    @Test
    void correctClassCreate() {

        var author = new Author("author");
        var genre = new Genre("genre");
        var book = new Book("BookName", author, genre);
        var comment = new Comment("25.06.2020", "misha", "test content", true, book);

        assertThat(book).isNotNull().isInstanceOf(Book.class);
        assertEquals("BookName", book.getName());
        assertThat(book.getAuthor()).isNotNull().isInstanceOf(Author.class);
        assertThat(book.getGenres()).isNotNull();
    }
}