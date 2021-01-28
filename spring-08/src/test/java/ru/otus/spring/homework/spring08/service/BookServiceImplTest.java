package ru.otus.spring.homework.spring08.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.otus.spring.homework.spring08.models.Author;
import ru.otus.spring.homework.spring08.models.Book;
import ru.otus.spring.homework.spring08.models.Genre;
import ru.otus.spring.homework.spring08.repositories.BookRepository;
import ru.otus.spring.homework.spring08.repositories.CommentRepository;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DisplayName("Сервис для работы с книгами должен")
@SpringBootTest(classes = BookServiceImpl.class)
class BookServiceImplTest {

    @MockBean BookRepository bookRepository;
    @MockBean CommentRepository commentRepository;
    @Autowired BookServiceImpl bookService;

    @DisplayName("возвращать книгу при вводе ее ID в консоль")
    @Test
    void getBook() {
        var expectedBook = new Book("Murder on the Orient Express",
                new Author("Agatha Christie"), new Genre("Detective"));
        Mockito.when(bookRepository.findById(expectedBook.getId()))
                .thenReturn(Optional.of(new Book("Murder on the Orient Express",
                        new Author("Agatha Christie"), new Genre("Detective"))));
        var actualBook = bookService.getBook(expectedBook.getId());
        assertThat(actualBook).usingRecursiveComparison().isEqualTo(expectedBook);
    }
}