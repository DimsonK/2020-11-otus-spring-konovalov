package ru.otus.spring.homework.spring05.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.otus.spring.homework.spring05.dao.BookDao;
import ru.otus.spring.homework.spring05.domain.Author;
import ru.otus.spring.homework.spring05.domain.Book;
import ru.otus.spring.homework.spring05.domain.Genre;
import ru.otus.spring.homework.spring05.shell.ShellReader;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DisplayName("Сервис для работы с книгами должен")
@SpringBootTest(classes = BookServiceImpl.class)
class BookServiceImplTest {

    @MockBean BookDao bookDao;
    @MockBean AuthorService authorService;
    @MockBean GenreService genreService;
    @MockBean ShellReader shellReader;
    @Autowired BookServiceImpl bookService;


    @DisplayName("возвращать книгу при вводе ее ID в консоль")
    @Test
    void getAuthor() {
        var expectedBook = new Book(1, "Murder on the Orient Express",
                new Author(1, "Agatha Christie"), new Genre(1, "Detective"));
        Mockito.when(bookDao.getById(expectedBook.getId()))
                .thenReturn(new Book(1, "Murder on the Orient Express",
                        new Author(1, "Agatha Christie"), new Genre(1, "Detective")));
        Mockito.when(shellReader.readShell()).thenReturn("1");
        var actualBook = bookService.getBook();
        assertThat(actualBook).usingRecursiveComparison().isEqualTo(expectedBook);
    }
}