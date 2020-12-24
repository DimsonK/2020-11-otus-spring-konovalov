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
import ru.otus.spring.homework.spring05.domain.Genre;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayName("Dao для работы с жанрами должно")
@JdbcTest
@Import(GenreDaoJdbc.class)
@Transactional(propagation = Propagation.REQUIRES_NEW)
class GenreDaoJdbcTest {
    private static final int EXPECTED_GENRES_COUNT = 4;

    @Autowired
    GenreDao genreDao;

    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    @DisplayName("возвращать ожидаемое количество жанров в БД")
    @Test
    void shouldReturnExpectedGenreCount() {
        int actualGenreCount = genreDao.count();
        assertThat(actualGenreCount).isEqualTo(EXPECTED_GENRES_COUNT);
    }

    @DisplayName("добавлять жанр в БД")
    @Test
    void shouldInsertGenre() {
        var expectedGenre = new Genre(10, "Фантастика");
        genreDao.insert(expectedGenre);
        var actualGenre = genreDao.getById(expectedGenre.getId());
        assertThat(actualGenre).isNotNull().usingRecursiveComparison().isEqualTo(expectedGenre);
    }

    @DisplayName("обновлять жанр в БД")
    @Test
    void shouldUpdateGenre() {
        var expectedGenre = new Genre(1, "Фантастика");
        var oldGenre = genreDao.getById(expectedGenre.getId());
        genreDao.update(expectedGenre);
        var actualGenre = genreDao.getById(oldGenre.getId());
        assertThat(actualGenre).usingRecursiveComparison().isNotEqualTo(oldGenre);
        assertThat(actualGenre).usingRecursiveComparison().isEqualTo(expectedGenre);
    }

    @DisplayName("возвращать ожидаемый жанр по его id")
    @Test
    void shouldReturnExpectedGenreById() {
        var expectedGenre = new Genre(1, "Detective");
        var actualGenre = genreDao.getById(expectedGenre.getId());
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
        var actualGenreList = genreDao.getAll();
        assertThat(actualGenreList)
                .usingFieldByFieldElementComparator()
                .containsExactlyInAnyOrderElementsOf(expectedGenreList);
    }

    @DisplayName("удалять заданный жанр по его id")
    @Test
    void shouldCorrectDeleteGenreById() {
        final var genreForDeleteId = 1;
        genreDao.deleteById(genreForDeleteId);
        assertThatThrownBy(() -> genreDao.getById(genreForDeleteId))
                .isInstanceOf(EmptyResultDataAccessException.class);
    }
}