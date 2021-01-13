package ru.otus.spring.homework.spring06.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.otus.spring.homework.spring06.models.Genre;
import ru.otus.spring.homework.spring06.repositories.GenreRepository;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DisplayName("Сервис для работы с жанрами должен")
@SpringBootTest(classes = GenreServiceImpl.class)
class GenreServiceImplTest {

    @MockBean GenreRepository genreRepository;
    @Autowired GenreServiceImpl genreService;

    @DisplayName("возвращать жанр при вводе его ID в консоль")
    @Test
    void getGenre() {
        var expectedGenre = new Genre(1, "Detective");
        Mockito.when(genreRepository.findById(expectedGenre.getId()))
                .thenReturn(Optional.of(new Genre(1, "Detective")));
        var actualGenre = genreService.getGenre(1L);
        assertThat(actualGenre).usingRecursiveComparison().isEqualTo(expectedGenre);
    }
}