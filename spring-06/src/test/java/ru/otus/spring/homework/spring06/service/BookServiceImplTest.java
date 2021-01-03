package ru.otus.spring.homework.spring06.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.otus.spring.homework.spring06.models.Author;
import ru.otus.spring.homework.spring06.models.Book;
import ru.otus.spring.homework.spring06.models.Genre;
import ru.otus.spring.homework.spring06.repositories.BookRepository;
import ru.otus.spring.homework.spring06.shell.ShellReader;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DisplayName("Сервис для работы с книгами должен")
@SpringBootTest(classes = BookServiceImpl.class)
class BookServiceImplTest {

    @MockBean BookRepository bookRepository;
    @MockBean AuthorService authorService;
    @MockBean GenreService genreService;
    @MockBean ShellReader shellReader;
    @Autowired BookServiceImpl bookService;

    @DisplayName("возвращать книгу при вводе ее ID в консоль")
    @Test
    void getAuthor() {
        var expectedBook = new Book(1, "Murder on the Orient Express",
                new Author(1, "Agatha Christie"), List.of(new Genre(1, "Detective")), null);
        Mockito.when(bookRepository.findById(expectedBook.getId()))
                .thenReturn(Optional.of(new Book(1, "Murder on the Orient Express",
                        new Author(1, "Agatha Christie"), List.of(new Genre(1, "Detective")), null)));
        Mockito.when(shellReader.readShell()).thenReturn("1");
        var actualBook = bookService.getBook();
        assertThat(actualBook).usingRecursiveComparison().isEqualTo(expectedBook);
    }
}