package ru.otus.spring.homework.spring08.repositories;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import ru.otus.spring.homework.spring08.AbstractRepositoryTest;
import ru.otus.spring.homework.spring08.models.Genre;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

@DisplayName("Репозиторий для работы с жанрами должно")
class GenreRepositoryTest extends AbstractRepositoryTest {

    private static final long EXPECTED_GENRES_COUNT = 4L;

    @Autowired GenreRepository genreRepository;

    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    @DisplayName("возвращать ожидаемое количество жанров в БД")
    @Test
    void shouldReturnExpectedGenreCount() {
        long actualGenreCount = genreRepository.count();
        assertThat(actualGenreCount).isEqualTo(EXPECTED_GENRES_COUNT);
    }

    @DisplayName("добавлять жанр в БД")
    @Test
    void shouldInsertGenre() {
        var expectedGenre = genreRepository.save(new Genre("Фантастика"));
        var actualGenre = genreRepository.findById(expectedGenre.getId()).orElse(null);
        assertThat(actualGenre).isNotNull().usingRecursiveComparison().isEqualTo(expectedGenre);
    }

    @DisplayName("обновлять жанр в БД")
    @Test
    void shouldUpdateGenre() {
        var expectedGenre = genreRepository.save(new Genre("Фантастика"));
        var oldGenre = genreRepository.findById(expectedGenre.getId()).orElse(null);
        assertNotNull(oldGenre);
        expectedGenre.setName("Приключения");
        expectedGenre = genreRepository.save(expectedGenre);
        var actualGenre = genreRepository.findById(oldGenre.getId()).orElse(null);
        assertThat(actualGenre).usingRecursiveComparison().isNotEqualTo(oldGenre);
        assertThat(actualGenre).usingRecursiveComparison().isEqualTo(expectedGenre);
    }

    @DisplayName("возвращать ожидаемый жанр по его id")
    @Test
    void shouldReturnExpectedGenreById() {
        var expectedGenre = genreRepository.save(new Genre("Detective"));
        var actualGenre = genreRepository.findById(expectedGenre.getId()).orElse(null);
        assertThat(actualGenre).isNotNull().usingRecursiveComparison().isEqualTo(expectedGenre);
    }

    @DisplayName("возвращать ожидаемый список жанров")
    @Test
    void shouldReturnExpectedGenresList() {
        var expectedGenreList = List.of(
                new Genre("Detective"),
                new Genre("History"),
                new Genre("Fantasy"),
                new Genre("Horror"));
        var actualGenreList = genreRepository.findAll();
        assertThat(actualGenreList)
                .usingElementComparatorIgnoringFields("id")
                .containsExactlyInAnyOrderElementsOf(expectedGenreList);
    }

    @DisplayName("удалять заданный жанр по его id")
    @Test
    void shouldCorrectDeleteGenreById() {
        var expectedGenre = new Genre("10", "Фантастика");
        expectedGenre = genreRepository.save(expectedGenre);
        var actualGenre = genreRepository.findById(expectedGenre.getId()).orElse(null);
        assertThat(actualGenre).isNotNull().usingRecursiveComparison().isEqualTo(expectedGenre);
        genreRepository.deleteById(actualGenre.getId());
        assertNull(genreRepository.findById(actualGenre.getId()).orElse(null));
    }
}