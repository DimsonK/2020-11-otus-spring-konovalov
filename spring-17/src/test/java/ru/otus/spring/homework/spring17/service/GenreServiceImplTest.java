package ru.otus.spring.homework.spring17.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.otus.spring.homework.spring17.mappers.GenreMapper;
import ru.otus.spring.homework.spring17.models.entity.Genre;
import ru.otus.spring.homework.spring17.repositories.GenreRepository;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DisplayName("Сервис для работы с жанрами должен")
@SpringBootTest(classes = {GenreMapper.class, GenreServiceImpl.class})
class GenreServiceImplTest {

    @MockBean GenreRepository genreRepository;
    @Autowired GenreMapper genreMapper;
    @Autowired GenreServiceImpl genreService;

    @DisplayName("возвращать жанр при вводе его ID в консоль")
    @Test
    void getGenre() {
        var expectedGenre = genreMapper.toDto(new Genre(1L, "Detective"));
        Mockito.when(genreRepository.findById(Long.parseLong(expectedGenre.getId())))
                .thenReturn(Optional.of(new Genre(1L, "Detective")));
        var actualGenre = genreService.getGenre(1L);
        assertThat(actualGenre).usingRecursiveComparison().isEqualTo(expectedGenre);
    }
}