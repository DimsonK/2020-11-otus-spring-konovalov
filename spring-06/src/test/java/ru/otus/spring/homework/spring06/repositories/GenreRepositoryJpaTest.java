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
import ru.otus.spring.homework.spring06.models.Genre;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

@DisplayName("Репозиторий для работы с жанрами должно")
@DataJpaTest
@Import(GenreRepositoryJpa.class)
@Transactional(propagation = Propagation.REQUIRES_NEW)
class GenreRepositoryJpaTest {

    private static final long EXPECTED_GENRES_COUNT = 4L;

    @Autowired TestEntityManager em;

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
        var expectedGenre = new Genre(10, "Фантастика");
        genreRepository.save(expectedGenre);
        var actualGenre = genreRepository.findById(expectedGenre.getId()).orElse(null);
        assertThat(actualGenre).isNotNull().usingRecursiveComparison().isEqualTo(expectedGenre);
    }

    @DisplayName("обновлять жанр в БД")
    @Test
    void shouldUpdateGenre() {
        var expectedGenre = new Genre(1, "Фантастика");
        var oldGenre = genreRepository.findById(expectedGenre.getId()).orElse(null);
        assertNotNull(oldGenre);
        em.detach(oldGenre);
        genreRepository.save(expectedGenre);
        var actualGenre = genreRepository.findById(oldGenre.getId()).orElse(null);
        assertThat(actualGenre).usingRecursiveComparison().isNotEqualTo(oldGenre);
        assertThat(actualGenre).usingRecursiveComparison().isEqualTo(expectedGenre);
    }

    @DisplayName("возвращать ожидаемый жанр по его id")
    @Test
    void shouldReturnExpectedGenreById() {
        var expectedGenre = new Genre(1, "Detective");
        var actualGenre = genreRepository.findById(expectedGenre.getId()).orElse(null);
        assertThat(actualGenre).isNotNull().usingRecursiveComparison().isEqualTo(expectedGenre);
    }

    @DisplayName("возвращать ожидаемый список жанров")
    @Test
    void shouldReturnExpectedGenresList() {
        var expectedGenreList = List.of(
                new Genre(1, "Detective"),
                new Genre(2, "History"),
                new Genre(3, "Fantasy"),
                new Genre(4, "Horror"));
        var actualGenreList = genreRepository.findAll();
        assertThat(actualGenreList)
                .usingFieldByFieldElementComparator()
                .containsExactlyInAnyOrderElementsOf(expectedGenreList);
    }

    @DisplayName("удалять заданный жанр по его id")
    @Test
    void shouldCorrectDeleteGenreById() {
        var expectedGenre = new Genre(0, "Фантастика");
        expectedGenre = genreRepository.save(expectedGenre);
        var actualGenre = genreRepository.findById(expectedGenre.getId()).orElse(null);
        assertThat(actualGenre).isNotNull().usingRecursiveComparison().isEqualTo(expectedGenre);
        em.detach(actualGenre);
        genreRepository.deleteById(actualGenre.getId());
        assertNull(genreRepository.findById(actualGenre.getId()).orElse(null));
    }
}