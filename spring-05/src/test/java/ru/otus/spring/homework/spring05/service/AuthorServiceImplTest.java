package ru.otus.spring.homework.spring05.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.otus.spring.homework.spring05.dao.AuthorDao;
import ru.otus.spring.homework.spring05.domain.Author;
import ru.otus.spring.homework.spring05.shell.ShellReader;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DisplayName("Сервис для работы с авторами должен")
@SpringBootTest(classes = AuthorServiceImpl.class)
class AuthorServiceImplTest {

    @MockBean AuthorDao authorDao;
    @MockBean ShellReader shellReader;
    @Autowired AuthorServiceImpl authorService;


    @DisplayName("возвращать автора при вводе его ID в консоль")
    @Test
    void getAuthor() {
        var expectedAuthor = new Author(1, "Agatha Christie");
        Mockito.when(authorDao.getById(expectedAuthor.getId()))
                .thenReturn(new Author(1, "Agatha Christie"));
        Mockito.when(shellReader.readShell()).thenReturn("1");
        var actualAuthor = authorService.getAuthor();
        assertThat(actualAuthor).usingRecursiveComparison().isEqualTo(expectedAuthor);
    }
}