package ru.otus.spring.homework.spring06.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.otus.spring.homework.spring06.models.Genre;
import ru.otus.spring.homework.spring06.repositories.GenreRepository;
import ru.otus.spring.homework.spring06.shell.ShellReader;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DisplayName("Сервис для работы с жанрами должен")
@SpringBootTest(classes = GenreServiceImpl.class)
class GenreServiceImplTest {

    @MockBean GenreRepository genreRepository;
    @MockBean ShellReader shellReader;
    @Autowired GenreServiceImpl genreService;

    @DisplayName("возвращать жанр при вводе его ID в консоль")
    @Test
    void getAuthor() {
        var expectedGenre = new Genre(1, "Detective");
        Mockito.when(genreRepository.findById(expectedGenre.getId()))
                .thenReturn(Optional.of(new Genre(1, "Detective")));
        Mockito.when(shellReader.readShell()).thenReturn("1");
        var actualGenre = genreService.getGenre();
        assertThat(actualGenre).usingRecursiveComparison().isEqualTo(expectedGenre);
    }
}