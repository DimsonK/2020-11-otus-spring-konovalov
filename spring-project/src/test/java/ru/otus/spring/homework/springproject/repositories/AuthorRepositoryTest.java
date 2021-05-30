package ru.otus.spring.homework.springproject.repositories;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.homework.springproject.models.entity.Author;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

@DisplayName("Репозиторий для работы с авторами должен")
@DataJpaTest
@Transactional(propagation = Propagation.REQUIRES_NEW)
class AuthorRepositoryTest {

    private static final long EXPECTED_AUTHORS_COUNT = 9L;

    @Autowired
    TestEntityManager em;

    @Autowired
    AuthorRepository authorRepository;

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
        var expectedAuthor = new Author(10L, "Tolstoy");
        authorRepository.save(expectedAuthor);
        var actualAuthor = authorRepository.findById(expectedAuthor.getId()).orElse(null);
        assertThat(actualAuthor).isNotNull().usingRecursiveComparison().isEqualTo(expectedAuthor);
    }

    @DisplayName("обновлять автора в БД")
    @Test
    void shouldUpdateAuthor() {
        var expectedAuthor = new Author(1L, "Dostoevsky");
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
        var expectedAuthor = new Author(1L, "Agatha Christie");
        var actualAuthor = authorRepository.findById(expectedAuthor.getId()).orElse(null);
        assertNotNull(actualAuthor);
        assertThat(actualAuthor)
            .isNotNull()
            .usingRecursiveComparison()
            .ignoringFields("createdAt", "createdBy", "modifiedAt", "modifiedBy")
            .isEqualTo(expectedAuthor);
    }

    @DisplayName("возвращать ожидаемый список авторов")
    @Test
    void shouldReturnExpectedAuthorsList() {
        var expectedAuthorList = List.of(
            new Author(1L, "Agatha Christie"),
            new Author(2L, "Alexandre Dumas"),
            new Author(3L, "Jules Gabriel Verne"),
            new Author(4L, "Edgar Allan Poe"),
            new Author(5L, "Stephen Edwin King"),
            new Author(6L, "Elizabeth McNeill"),
            new Author(7L, "E L James, Zachary Webber, et al."),
            new Author(8L, "Попов, Шойгу и Ко"),
            new Author(9L, "Уралвагонзавод и сотоварищи")
        );

        var actualAuthorList = authorRepository.findAll();

        assertThat(actualAuthorList)
            .usingFieldByFieldElementComparator()
            .usingElementComparatorIgnoringFields("createdAt", "createdBy", "modifiedAt", "modifiedBy")
            .containsExactlyInAnyOrderElementsOf(expectedAuthorList);
    }

    @DisplayName("удалять заданного автора по его id")
    @Test
    void shouldCorrectDeleteAuthorById() {
        var expectedAuthor = new Author(10L, "Tolstoy");
        authorRepository.save(expectedAuthor);
        var actualAuthor = authorRepository.findById(expectedAuthor.getId()).orElse(null);
        assertThat(actualAuthor).isNotNull().usingRecursiveComparison().isEqualTo(expectedAuthor);
        authorRepository.deleteById(actualAuthor.getId());
        assertNull(authorRepository.findById(actualAuthor.getId()).orElse(null));
    }
}
