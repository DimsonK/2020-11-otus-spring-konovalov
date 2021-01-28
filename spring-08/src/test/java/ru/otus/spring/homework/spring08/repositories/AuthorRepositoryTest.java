package ru.otus.spring.homework.spring08.repositories;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import ru.otus.spring.homework.spring08.AbstractRepositoryTest;
import ru.otus.spring.homework.spring08.models.Author;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Репозиторий для работы с авторами должен")
class AuthorRepositoryTest extends AbstractRepositoryTest {

    private static final long EXPECTED_AUTHORS_COUNT = 5L;

    @Autowired AuthorRepository authorRepository;

    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    @DisplayName("возвращать ожидаемое количество авторов в БД")
    @Test
    void shouldReturnExpectedAuthorCount() {
        long actualAuthorCount = authorRepository.count();
        assertThat(actualAuthorCount).isEqualTo(EXPECTED_AUTHORS_COUNT);
    }

    @DisplayName("добавлять автора в БД")
    @Test
    void shouldInsertAuthor() {
        var expectedAuthor = authorRepository.save(new Author("Tolstoy"));
        var actualAuthor = authorRepository.findById(expectedAuthor.getId()).orElse(null);
        assertThat(actualAuthor).isNotNull().usingRecursiveComparison().isEqualTo(expectedAuthor);
    }

    @DisplayName("обновлять автора в БД")
    @Test
    void shouldUpdateAuthor() {
        var expectedAuthor = authorRepository.save(new Author("Dostoevsky"));
        assertNotNull(expectedAuthor);
        expectedAuthor.setName("Tolstoy");
        authorRepository.save(expectedAuthor);
        var actualAuthor = authorRepository.findById(expectedAuthor.getId()).orElse(null);
        assertNotNull(actualAuthor);
        assertNotEquals("Dostoevsky", actualAuthor.getName());
        assertEquals("Tolstoy", actualAuthor.getName());
    }

    @DisplayName("возвращать ожидаемого автора по его id")
    @Test
    void shouldReturnExpectedAuthorById() {
        var expectedAuthor = authorRepository.save(new Author("Agatha Christie"));
        var actualAuthor = authorRepository.findById(expectedAuthor.getId()).orElse(null);
        assertThat(actualAuthor).isNotNull().usingRecursiveComparison().isEqualTo(expectedAuthor);
    }

    @DisplayName("возвращать ожидаемый список авторов")
    @Test
    void shouldReturnExpectedAuthorsList() {
        var expectedAuthorList = List.of(
                new Author("Agatha Christie"),
                new Author("Alexandre Dumas"),
                new Author("Jules Gabriel Verne"),
                new Author("Edgar Allan Poe"),
                new Author("Stephen Edwin King"));
        var actualAuthorList = authorRepository.findAll();

        assertThat(actualAuthorList)
                .usingElementComparatorIgnoringFields("id")
                .containsExactlyInAnyOrderElementsOf(expectedAuthorList);
    }

    @DisplayName("удалять заданного автора по его id")
    @Test
    void shouldCorrectDeleteAuthorById() {
        var expectedAuthor = new Author("Tolstoy");
        authorRepository.save(expectedAuthor);
        var actualAuthor = authorRepository.findById(expectedAuthor.getId()).orElse(null);
        assertThat(actualAuthor).isNotNull().usingRecursiveComparison().isEqualTo(expectedAuthor);
        authorRepository.deleteById(actualAuthor.getId());
        assertNull(authorRepository.findById(actualAuthor.getId()).orElse(null));
    }
}
