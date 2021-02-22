package ru.otus.spring.homework.spring11.repositories;

import lombok.extern.java.Log;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import ru.otus.spring.homework.spring11.AbstractRepositoryTest;
import ru.otus.spring.homework.spring11.models.entity.Author;
import ru.otus.spring.homework.spring11.models.entity.Book;
import ru.otus.spring.homework.spring11.models.entity.Genre;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;


@DisplayName("проверяет расширенные методы репозитория книг")
@Log
class BookRepositoryCustomImplTest extends AbstractRepositoryTest {

    @Autowired BookRepository bookRepository;
    @Autowired AuthorRepository authorRepository;
    @Autowired GenreRepository genreRepository;

    @DisplayName("поиск автора в книгах")
    @Test
    void authorExistsInBooks() {
        Example<Author> example = Example.of(new Author("Agatha Christie"));
        var author = authorRepository.findOne(example).block();
        assertThat(author).isNotNull();
        var exists = bookRepository.authorExistsInBooks(author).block();
        assertNotNull(exists);
        assertTrue(exists);
        author.setId("not_id");
        author.setName("Not Author");
        exists = bookRepository.authorExistsInBooks(author).block();
        assertNotNull(exists);
        assertFalse(exists);
    }

    @Test
    void genreExistsInBooks() {
        Example<Genre> example = Example.of(new Genre("Detective"));
        var genre = genreRepository.findOne(example).block();
        assertThat(genre).isNotNull();
        var exists = bookRepository.genreExistsInBooks(genre).block();
        assertNotNull(exists);
        assertTrue(exists);
        genre.setId("not_id");
        genre.setName("Not Genre");
        exists = bookRepository.genreExistsInBooks(genre).block();
        assertNotNull(exists);
        assertFalse(exists);
    }

    @DisplayName("меняет имена авторов в книгах")
    @Test
    void updateAuthorInBooks() {
        var exBook = new Book();
        exBook.setName("Murder on the Orient Express");
        Example<Book> example = Example.of(exBook);
        var expectedBook = bookRepository.findOne(example).block();
        assertThat(expectedBook).isNotNull();

        var expectedAuthor = expectedBook.getAuthor();
        var newAuthor = new Author(expectedAuthor.getId(), "NewAuthorName");
        bookRepository.updateAuthorInBooks(newAuthor);
        var actualBook = bookRepository.findOne(example).block();
        assertThat(actualBook).isNotNull();
        var actualAuthor = actualBook.getAuthor();

        assertEquals(expectedAuthor.getId(), actualAuthor.getId());
        assertNotEquals(expectedAuthor.getName(), actualAuthor.getName());
    }

    @DisplayName("меняет имена жанров в книгах")
    @Test
    void updateGenreInBooks() {
        var exBook = new Book();
        exBook.setName("Murder on the Orient Express");
        Example<Book> example = Example.of(exBook);
        var expectedBook = bookRepository.findOne(example).block();
        assertThat(expectedBook).isNotNull();

        var expectedGenre = expectedBook.getGenres().get(0);
        var newGenre = new Genre(expectedGenre.getId(), "NewGenreName");
        bookRepository.updateGenreInBooks(newGenre);
        var actualBook = bookRepository.findOne(example).block();
        assertThat(actualBook).isNotNull();
        var actualGenre = actualBook.getGenres().get(0);

        assertEquals(expectedGenre.getId(), actualGenre.getId());
        assertNotEquals(expectedGenre.getName(), actualGenre.getName());
    }
}