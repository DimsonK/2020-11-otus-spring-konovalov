package ru.otus.spring.homework.springproject.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.otus.spring.homework.springproject.mappers.AuthorMapper;
import ru.otus.spring.homework.springproject.mappers.BookMapper;
import ru.otus.spring.homework.springproject.mappers.CommentMapper;
import ru.otus.spring.homework.springproject.mappers.GenreMapper;
import ru.otus.spring.homework.springproject.models.entity.Author;
import ru.otus.spring.homework.springproject.models.entity.Book;
import ru.otus.spring.homework.springproject.models.entity.Genre;
import ru.otus.spring.homework.springproject.repositories.AuthorRepository;
import ru.otus.spring.homework.springproject.repositories.BookRepository;
import ru.otus.spring.homework.springproject.repositories.CommentRepository;
import ru.otus.spring.homework.springproject.repositories.GenreRepository;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DisplayName("Сервис для работы с книгами должен")
@SpringBootTest(classes = {
        AuthorMapper.class, GenreMapper.class,
        CommentMapper.class, BookMapper.class, BookServiceImpl.class})
class BookServiceImplTest {

    @MockBean BookRepository bookRepository;
    @MockBean CommentRepository commentRepository;
    @MockBean AuthorRepository authorRepository;
    @MockBean GenreRepository genreRepository;
    @Autowired AuthorMapper authorMapper;
    @Autowired GenreMapper genreMapper;
    @Autowired CommentMapper commentMapper;
    @Autowired BookMapper bookMapper;
    @Autowired BookServiceImpl bookService;

    @DisplayName("возвращать книгу при вводе ее ID в консоль")
    @Test
    void getBook() {
        var expectedBook = bookMapper.toDto(new Book(1L, "Murder on the Orient Express", 12, 2,
                new Author(1L, "Agatha Christie"), List.of(new Genre(1L, "Detective")), null, null));
        Mockito.when(bookRepository.findById(Long.parseLong(expectedBook.getId())))
                .thenReturn(Optional.of(new Book(1L, "Murder on the Orient Express", 12, 2,
                        new Author(1L, "Agatha Christie"), List.of(new Genre(1L, "Detective")), null, null)));
        var actualBook = bookService.getBook(1L);
        assertThat(actualBook).usingRecursiveComparison().isEqualTo(expectedBook);
    }

}
