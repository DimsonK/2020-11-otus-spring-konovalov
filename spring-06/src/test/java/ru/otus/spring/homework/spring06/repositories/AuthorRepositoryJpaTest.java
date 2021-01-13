package ru.otus.spring.homework.spring06.repositories;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.homework.spring06.models.Author;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

@DisplayName("Репозиторий для работы с авторами должен")
@DataJpaTest
@Import(AuthorRepositoryJpa.class)
@Transactional(propagation = Propagation.REQUIRES_NEW)
class AuthorRepositoryJpaTest {

    private static final long EXPECTED_AUTHORS_COUNT = 5L;

    @Autowired TestEntityManager em;

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
        var expectedAuthor = new Author(10, "Tolstoy");
        authorRepository.save(expectedAuthor);
        var actualAuthor = authorRepository.findById(expectedAuthor.getId()).orElse(null);
        assertThat(actualAuthor).isNotNull().usingRecursiveComparison().isEqualTo(expectedAuthor);
    }

    @DisplayName("обновлять автора в БД")
    @Test
    void shouldUpdateAuthor() {
        var expectedAuthor = new Author(1, "Dostoevsky");
        var oldAuthor = authorRepository.findById(expectedAuthor.getId()).orElse(null);
        assertNotNull(oldAuthor);
        em.detach(oldAuthor);
        authorRepository.save(expectedAuthor);
        var actualAuthor = authorRepository.findById(oldAuthor.getId()).orElse(null);
        assertThat(actualAuthor).usingRecursiveComparison().isNotEqualTo(oldAuthor);
        assertThat(actualAuthor).usingRecursiveComparison().isEqualTo(expectedAuthor);
    }

    @DisplayName("возвращать ожидаемого автора по его id")
    @Test
    void shouldReturnExpectedAuthorById() {
        var expectedAuthor = new Author(1, "Agatha Christie");
        var actualAuthor = authorRepository.findById(expectedAuthor.getId()).orElse(null);
        assertNotNull(actualAuthor);
        assertThat(actualAuthor).isNotNull().usingRecursiveComparison().isEqualTo(expectedAuthor);
    }

    @DisplayName("возвращать ожидаемый список авторов")
    @Test
    void shouldReturnExpectedAuthorsList() {
        var expectedAuthorList = List.of(
                new Author(1, "Agatha Christie"),
                new Author(2, "Alexandre Dumas"),
                new Author(3, "Jules Gabriel Verne"),
                new Author(4, "Edgar Allan Poe"),
                new Author(5, "Stephen Edwin King"));
        var actualAuthorList = authorRepository.findAll();

        assertThat(actualAuthorList)
                .usingFieldByFieldElementComparator()
                .containsExactlyInAnyOrderElementsOf(expectedAuthorList);
    }

    @DisplayName("удалять заданного автора по его id")
    @Test
    void shouldCorrectDeleteAuthorById() {
        var expectedAuthor = new Author(10, "Tolstoy");
        authorRepository.save(expectedAuthor);
        var actualAuthor = authorRepository.findById(expectedAuthor.getId()).orElse(null);
        assertThat(actualAuthor).isNotNull().usingRecursiveComparison().isEqualTo(expectedAuthor);
        em.detach(actualAuthor);
        authorRepository.deleteById(actualAuthor.getId());
        assertNull(authorRepository.findById(actualAuthor.getId()).orElse(null));
    }
}