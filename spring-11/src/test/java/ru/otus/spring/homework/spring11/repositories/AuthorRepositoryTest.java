package ru.otus.spring.homework.spring11.repositories;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.test.annotation.DirtiesContext;
import reactor.test.StepVerifier;
import ru.otus.spring.homework.spring11.AbstractRepositoryTest;
import ru.otus.spring.homework.spring11.models.entity.Author;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@DisplayName("Репозиторий для работы с авторами должен")
class AuthorRepositoryTest extends AbstractRepositoryTest {

    private static final long EXPECTED_AUTHORS_COUNT = 5L;

    @Autowired
    private AuthorRepository authorRepository;

    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    @DisplayName("возвращать ожидаемое количество авторов в БД")
    @Test
    void shouldReturnExpectedAuthorCount() {
        var actualAuthorCountMono = authorRepository.count();
        StepVerifier
                .create(actualAuthorCountMono)
                .assertNext(actualAuthorCount -> assertThat(actualAuthorCount).isEqualTo(EXPECTED_AUTHORS_COUNT))
                .expectComplete()
                .verify();
    }

    @DisplayName("добавлять автора в БД")
    @Test
    void shouldInsertAuthor() {
        var expectedAuthor = authorRepository.save(new Author("Tolstoy"));
        StepVerifier
                .create(expectedAuthor)
                .assertNext(author -> assertNotNull(author.getId()))
                .expectComplete()
                .verify();
    }

    @DisplayName("обновлять автора в БД")
    @Test
    void shouldUpdateAuthor() {
        var exampleAuthor = new Author("Agatha Christie");

        var existsAuthor = authorRepository.findOne(Example.of(exampleAuthor)).block();
        assertNotNull(existsAuthor);
        existsAuthor.setName("Dostoevsky");

        var updatedAuthor = authorRepository.save(existsAuthor);
        StepVerifier
                .create(updatedAuthor)
                .assertNext(author -> {
                    assertNotNull(author);
                    assertNotEquals("Agatha Christie", author.getName());
                    assertEquals("Dostoevsky", author.getName());
                })
                .expectComplete()
                .verify();
    }

    @DisplayName("возвращать ожидаемого автора по его id")
    @Test
    void shouldReturnExpectedAuthorById() {
        var expectedAuthor = authorRepository.save(new Author("Dostoevsky")).block();
        assertNotNull(expectedAuthor);
        var actualAuthor = authorRepository.findById(expectedAuthor.getId());
        StepVerifier
                .create(actualAuthor)
                .assertNext(author -> {
                    assertNotNull(author);
                    assertEquals("Dostoevsky", author.getName());
                })
                .expectComplete()
                .verify();
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
        var actualAuthorList = authorRepository.findAll()
                .as(StepVerifier::create)
                .assertNext(author -> {
                    author.setId(null);
                    assertThat(author).isNotNull().isIn(expectedAuthorList);
                    log.info(author.toString());
                })
                .assertNext(author -> {
                    author.setId(null);
                    assertThat(author).isNotNull().isIn(expectedAuthorList);
                    log.info(author.toString());
                })
                .assertNext(author -> {
                    author.setId(null);
                    assertThat(author).isNotNull().isIn(expectedAuthorList);
                    log.info(author.toString());
                })
                .assertNext(author -> {
                    author.setId(null);
                    assertThat(author).isNotNull().isIn(expectedAuthorList);
                    log.info(author.toString());
                })
                .assertNext(author -> {
                    author.setId(null);
                    assertThat(author).isNotNull().isIn(expectedAuthorList);
                    log.info(author.toString());
                })
                .verifyComplete();
    }

    @DisplayName("удалять заданного автора по его id")
    @Test
    void shouldCorrectDeleteAuthorById() {
        var expectedAuthor = new Author("Tolstoy");
        authorRepository.save(expectedAuthor).block();
        var actualAuthor = authorRepository.findById(expectedAuthor.getId()).block();
        assertThat(actualAuthor).isNotNull().usingRecursiveComparison().isEqualTo(expectedAuthor);
        authorRepository.deleteById(actualAuthor.getId()).block();
        authorRepository.findById(expectedAuthor.getId())
                .as(StepVerifier::create)
                .expectComplete()
                .verify();
    }
}
