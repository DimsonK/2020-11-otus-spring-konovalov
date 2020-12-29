package ru.otus.spring.homework.spring05.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.homework.spring05.domain.Author;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayName("Dao для работы с авторами должно")
@JdbcTest
@Import(AuthorDaoJdbc.class)
@Transactional(propagation = Propagation.REQUIRES_NEW)
class AuthorDaoJdbcTest {

    private static final int EXPECTED_AUTHORS_COUNT = 5;

    @Autowired
    AuthorDao authorDao;

    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    @DisplayName("возвращать ожидаемое количество авторов в БД")
    @Test
    void shouldReturnExpectedAuthorCount() {
        int actualAuthorCount = authorDao.count();
        assertThat(actualAuthorCount).isEqualTo(EXPECTED_AUTHORS_COUNT);
    }

    @DisplayName("добавлять автора в БД")
    @Test
    void shouldInsertAuthor() {
        var expectedAuthor = new Author(10, "Tolstoy");
        authorDao.insert(expectedAuthor);
        var actualAuthor = authorDao.getById(expectedAuthor.getId());
        assertThat(actualAuthor).isNotNull().usingRecursiveComparison().isEqualTo(expectedAuthor);
    }

    @DisplayName("обновлять автора в БД")
    @Test
    void shouldUpdateAuthor() {
        var expectedAuthor = new Author(1, "Dostoevsky");
        var oldAuthor = authorDao.getById(expectedAuthor.getId());
        authorDao.update(expectedAuthor);
        var actualAuthor = authorDao.getById(oldAuthor.getId());
        assertThat(actualAuthor).usingRecursiveComparison().isNotEqualTo(oldAuthor);
        assertThat(actualAuthor).usingRecursiveComparison().isEqualTo(expectedAuthor);
    }

    @DisplayName("возвращать ожидаемого автора по его id")
    @Test
    void shouldReturnExpectedAuthorById() {
        var expectedAuthor = new Author(1, "Agatha Christie");
        var actualAuthor = authorDao.getById(expectedAuthor.getId());
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
        var actualAuthorList = authorDao.getAll();

        assertThat(actualAuthorList)
                .usingFieldByFieldElementComparator()
                .containsExactlyInAnyOrderElementsOf(expectedAuthorList);
    }

    @DisplayName("удалять заданного автора по его id")
    @Test
    void shouldCorrectDeleteAuthorById() {
        final var authorForDeleteId = 1;
        authorDao.deleteById(authorForDeleteId);
        assertThatThrownBy(() -> authorDao.getById(authorForDeleteId))
                .isInstanceOf(EmptyResultDataAccessException.class);
    }
}