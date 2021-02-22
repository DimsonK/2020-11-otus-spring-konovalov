package ru.otus.spring.homework.spring11.repositories;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.test.annotation.DirtiesContext;
import reactor.test.StepVerifier;
import ru.otus.spring.homework.spring11.AbstractRepositoryTest;
import ru.otus.spring.homework.spring11.models.entity.Genre;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Репозиторий для работы с жанрами должно")
class GenreRepositoryTest extends AbstractRepositoryTest {

    private static final long EXPECTED_GENRES_COUNT = 4L;

    @Autowired GenreRepository genreRepository;

    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    @DisplayName("возвращать ожидаемое количество жанров в БД")
    @Test
    void shouldReturnExpectedGenreCount() {
        var actualGenreCount = genreRepository.count();
        StepVerifier
                .create(actualGenreCount)
                .assertNext(actualAuthorCount -> assertThat(actualAuthorCount).isEqualTo(EXPECTED_GENRES_COUNT))
                .expectComplete()
                .verify();
    }

    @DisplayName("добавлять жанр в БД")
    @Test
    void shouldInsertGenre() {
        var expectedGenre = genreRepository.save(new Genre("Фантастика"));
        StepVerifier
                .create(expectedGenre)
                .assertNext(genre -> assertNotNull(genre.getId()))
                .expectComplete()
                .verify();
    }

    @DisplayName("обновлять жанр в БД")
    @Test
    void shouldUpdateGenre() {
        var exampleGenre = new Genre("Detective");
        var existsGenre = genreRepository.findOne(Example.of(exampleGenre)).block();
        assertNotNull(existsGenre);
        existsGenre.setName("Comedy");
        var updatedGenre = genreRepository.save(existsGenre);
        StepVerifier
                .create(updatedGenre)
                .assertNext(genre -> {
                    assertNotNull(genre);
                    assertNotEquals("Detective", genre.getName());
                    assertEquals("Comedy", genre.getName());
                })
                .expectComplete()
                .verify();
    }

    @DisplayName("возвращать ожидаемый жанр по его id")
    @Test
    void shouldReturnExpectedGenreById() {
        var expectedGenre = genreRepository.save(new Genre("Comedy")).block();
        assertNotNull(expectedGenre);
        var actualGenre = genreRepository.findById(expectedGenre.getId());
        StepVerifier
                .create(actualGenre)
                .assertNext(genre -> {
                    assertNotNull(genre);
                    assertEquals("Comedy", genre.getName());
                })
                .expectComplete()
                .verify();
    }

    @DisplayName("возвращать ожидаемый список жанров")
    @Test
    void shouldReturnExpectedGenresList() {
        var expectedGenreList = List.of(
                new Genre("Detective"),
                new Genre("History"),
                new Genre("Fantasy"),
                new Genre("Horror"));
        var actualGenreList = genreRepository.findAll()
                .as(StepVerifier::create)
                .assertNext(genre -> {
                    genre.setId(null);
                    assertThat(genre).isNotNull().isIn(expectedGenreList);
                })
                .assertNext(genre -> {
                    genre.setId(null);
                    assertThat(genre).isNotNull().isIn(expectedGenreList);
                })
                .assertNext(genre -> {
                    genre.setId(null);
                    assertThat(genre).isNotNull().isIn(expectedGenreList);
                })
                .assertNext(genre -> {
                    genre.setId(null);
                    assertThat(genre).isNotNull().isIn(expectedGenreList);
                })
                .verifyComplete();
    }

    @DisplayName("удалять заданный жанр по его id")
    @Test
    void shouldCorrectDeleteGenreById() {
        var expectedGenre = genreRepository.save(new Genre("Фантастика")).block();
        assertNotNull(expectedGenre);
        var actualGenre = genreRepository.findById(expectedGenre.getId()).block();
        assertThat(actualGenre).isNotNull().usingRecursiveComparison().isEqualTo(expectedGenre);
        genreRepository.deleteById(actualGenre.getId()).block();
        genreRepository.findById(expectedGenre.getId())
                .as(StepVerifier::create)
                .expectComplete()
                .verify();
    }
}