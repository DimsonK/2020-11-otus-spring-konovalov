package ru.otus.spring.homework.spring11.controllers;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.spring.homework.spring11.mappers.AuthorMapper;
import ru.otus.spring.homework.spring11.mappers.BookMapper;
import ru.otus.spring.homework.spring11.mappers.CommentMapper;
import ru.otus.spring.homework.spring11.mappers.GenreMapper;
import ru.otus.spring.homework.spring11.models.dto.AuthorDto;
import ru.otus.spring.homework.spring11.models.dto.BookDto;
import ru.otus.spring.homework.spring11.models.dto.GenreDto;
import ru.otus.spring.homework.spring11.repositories.BookRepository;
import ru.otus.spring.homework.spring11.repositories.CommentRepository;

import java.util.List;

import static org.mockito.ArgumentMatchers.*;

@DisplayName("Контроллер книг должен")
@Slf4j
@WebFluxTest(controllers = BookController.class)
@Import({BookMapper.class, AuthorMapper.class, GenreMapper.class, CommentMapper.class})
class BookControllerTest {

    @MockBean
    private BookRepository bookRepository;
    @MockBean
    private CommentRepository commentRepository;

    @Autowired
    private BookMapper bookMapper;
    @Autowired
    private AuthorMapper authorMapper;
    @Autowired
    private GenreMapper genreMapper;
    @Autowired
    private CommentMapper commentMapper;
    @Autowired
    private WebTestClient webClient;

    @DisplayName("возвращать все книги")
    @Test
    void getBooks() {
        var books = List.of(
                new BookDto("1", "Murder on the Orient Express",
                        new AuthorDto("1", "Agatha Christie"), List.of(new GenreDto("1", "Detective")), List.of()),
                new BookDto("2", "The Three Musketeers",
                        new AuthorDto("2", "Alexandre Dumas"), List.of(new GenreDto("2", "History")), List.of()),
                new BookDto("3", "Twenty Thousand Leagues Under the Sea",
                        new AuthorDto("3", "Jules Gabriel Verne"), List.of(new GenreDto("3", "Fantasy")), List.of()),
                new BookDto("4", "The Gold Bug",
                        new AuthorDto("4", "Edgar Allan Poe"), List.of(new GenreDto("3", "Fantasy")), List.of()),
                new BookDto("5", "It",
                        new AuthorDto("5", "Stephen Edwin King"), List.of(new GenreDto("4", "Horror")), List.of()));
        Mockito.when(commentRepository.findByBookId(any(String.class))).thenReturn(Flux.fromIterable(List.of()));
        Mockito.when(bookRepository.findAll()).thenReturn(Flux.fromIterable(bookMapper.toEntityList(books)));
        this.webClient.get()
                .uri("/api/book")
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(BookDto.class)
                .hasSize(5)
                .isEqualTo(books);
    }

    @DisplayName("возвращать книги содержащие в названии определенное слово")
    @Test
    void getBooksSearch() {
        var books = List.of(
                new BookDto("1", "Murder on the Orient Express",
                        new AuthorDto("1", "Agatha Christie"), List.of(new GenreDto("1", "Detective")), List.of()));
        Mockito.when(commentRepository.findByBookId(any(String.class))).thenReturn(Flux.fromIterable(List.of()));
        Mockito.when(bookRepository.findBookByNameContaining("Murder")).thenReturn(Flux.fromIterable(bookMapper.toEntityList(List.of(books.get(0)))));
        this.webClient.get()
                .uri("/api/book?search=Murder")
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(BookDto.class)
                .hasSize(1)
                .isEqualTo(List.of(books.get(0)));
    }

    @DisplayName("возвращать книгу по ID")
    @Test
    void getBook() {
        var book = new BookDto("3", "Twenty Thousand Leagues Under the Sea",
                new AuthorDto("3", "Jules Gabriel Verne"), List.of(new GenreDto("3", "Fantasy")), List.of());
        Mockito.when(commentRepository.findByBookId(any(String.class))).thenReturn(Flux.fromIterable(List.of()));
        Mockito.when(bookRepository.findById(book.getId())).thenReturn(Mono.just(bookMapper.toEntity(book)));
        this.webClient.get()
                .uri("/api/book/3")
                .exchange()
                .expectStatus().isOk()
                .expectBody(BookDto.class)
                .isEqualTo(book);
    }

    @DisplayName("добавлять книгу")
    @Test
    void addBook() {
        var newBook = new BookDto(null, "The ABC Murders",
                new AuthorDto("1", "Agatha Christie"), List.of(new GenreDto("1", "Detective")), List.of());
        var actualBook = new BookDto("10", "The ABC Murders",
                new AuthorDto("1", "Agatha Christie"), List.of(new GenreDto("1", "Detective")), List.of());
        Mockito.when(bookRepository.save(bookMapper.toEntity(newBook))).thenReturn(Mono.just(bookMapper.toEntity(actualBook)));
        this.webClient.post()
                .uri("/api/book")
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(newBook))
                .exchange()
                .expectStatus().isOk()
                .expectBody(BookDto.class)
                .isEqualTo(actualBook);
    }

    @DisplayName("изменять книгу")
    @Test
    void updateBook() {
        var updatedBook = new BookDto("1", "Murder Made Easy",
                new AuthorDto("1", "Agatha Christie"), List.of(new GenreDto("1", "Detective")), List.of());
        Mockito.when(commentRepository.findByBookId(any(String.class))).thenReturn(Flux.fromIterable(List.of()));
        Mockito.when(bookRepository.save(bookMapper.toEntity(updatedBook))).thenReturn(Mono.just(bookMapper.toEntity(updatedBook)));
        this.webClient.put()
                .uri("/api/book/1")
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(updatedBook))
                .exchange()
                .expectStatus().isOk()
                .expectBody(BookDto.class)
                .isEqualTo(updatedBook);
    }

    @DisplayName("удалять книгу")
    @Test
    void deleteBook() throws Exception {
        var actualBook = new BookDto("1", "Murder on the Orient Express",
                new AuthorDto("1", "Agatha Christie"), List.of(new GenreDto("1", "Detective")), List.of());
        Mockito.when(bookRepository.existsById(actualBook.getId())).thenReturn(Mono.just(Boolean.TRUE));
        Mockito.when(bookRepository.deleteById(actualBook.getId())).thenReturn(Mono.empty());
        Mockito.when(commentRepository.deleteByBookId(actualBook.getId())).thenReturn(Mono.empty());
        this.webClient.delete()
                .uri("/api/book/1")
                .exchange()
                .expectStatus().isOk();
    }
}