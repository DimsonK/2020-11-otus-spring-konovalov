package ru.otus.spring.homework.spring05.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.otus.spring.homework.spring05.dao.GenreDao;
import ru.otus.spring.homework.spring05.domain.Genre;
import ru.otus.spring.homework.spring05.shell.ShellReader;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DisplayName("Сервис для работы с жанрами должен")
@SpringBootTest(classes = GenreServiceImpl.class)
class GenreServiceImplTest {

    @MockBean GenreDao genreDao;
    @MockBean ShellReader shellReader;
    @Autowired GenreServiceImpl genreService;


    @DisplayName("возвращать жанр при вводе его ID в консоль")
    @Test
    void getAuthor() {
        var expectedGenre = new Genre(1, "Detective");
        Mockito.when(genreDao.getById(expectedGenre.getId()))
                .thenReturn(new Genre(1, "Detective"));
        Mockito.when(shellReader.readShell()).thenReturn("1");
        var actualGenre = genreService.getGenre();
        assertThat(actualGenre).usingRecursiveComparison().isEqualTo(expectedGenre);
    }
}