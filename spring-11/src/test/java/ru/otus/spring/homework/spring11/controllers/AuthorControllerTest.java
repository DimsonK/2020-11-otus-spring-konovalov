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
import ru.otus.spring.homework.spring11.models.dto.AuthorDto;
import ru.otus.spring.homework.spring11.repositories.AuthorRepository;
import ru.otus.spring.homework.spring11.repositories.BookRepository;

import java.util.List;

import static org.mockito.Mockito.doNothing;

@DisplayName("Контроллер авторов должен")
@Slf4j
@WebFluxTest(controllers = AuthorController.class)
@Import(AuthorMapper.class)
class AuthorControllerTest {

    @MockBean
    private AuthorRepository authorRepository;
    @MockBean
    private BookRepository bookRepository;

    @Autowired
    private AuthorMapper authorMapper;
    @Autowired
    private WebTestClient webClient;

    @DisplayName("возвращать всех авторов")
    @Test
    void getAuthors() {
        var authors = List.of(
                new AuthorDto("1", "Agatha Christie"),
                new AuthorDto("2", "Alexandre Dumas"),
                new AuthorDto("3", "Jules Gabriel Verne"),
                new AuthorDto("4", "Edgar Allan Poe"),
                new AuthorDto("5", "Stephen Edwin King")
        );
        Mockito.when(authorRepository.findAll()).thenReturn(Flux.fromIterable(authorMapper.toEntityList(authors)));
        this.webClient.get()
                .uri("/api/author")
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(AuthorDto.class)
                .hasSize(5)
                .isEqualTo(authors);
    }

    @DisplayName("возвращать автора по его ID")
    @Test
    void getAuthor() {
        var author = new AuthorDto("1", "Agatha Christie");
        Mockito.when(authorRepository.findById("1")).thenReturn(Mono.just(authorMapper.toEntity(author)));
        this.webClient.get()
                .uri("/api/author/1")
                .exchange()
                .expectStatus().isOk()
                .expectBody(AuthorDto.class)
                .isEqualTo(author);
    }

    @DisplayName("добавлять автора")
    @Test
    void addAuthor() {
        var newAuthor = new AuthorDto(null, "Veniamin");
        var actualAuthor = new AuthorDto("10", "Veniamin");
        Mockito.when(authorRepository.existsAuthorByName(newAuthor.getName())).thenReturn(Mono.just(Boolean.FALSE));
        Mockito.when(authorRepository.save(authorMapper.toEntity(newAuthor))).thenReturn(Mono.just(authorMapper.toEntity(actualAuthor)));
        this.webClient.post()
                .uri("/api/author")
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(newAuthor))
                .exchange()
                .expectStatus().isOk()
                .expectBody(AuthorDto.class)
                .isEqualTo(actualAuthor);
    }

    @DisplayName("изменять автора")
    @Test
    void updateAuthor() {
        var updatedAuthor = new AuthorDto("1", "Veniamin Dorofeev");
        Mockito.when(authorRepository.existsById(updatedAuthor.getId())).thenReturn(Mono.just(Boolean.TRUE));
        doNothing().when(bookRepository).updateAuthorInBooks(authorMapper.toEntity(updatedAuthor));
        Mockito.when(authorRepository.save(authorMapper.toEntity(updatedAuthor))).thenReturn(Mono.just(authorMapper.toEntity(updatedAuthor)));
        this.webClient.put()
                .uri("/api/author/1")
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(updatedAuthor))
                .exchange()
                .expectStatus().isOk()
                .expectBody(AuthorDto.class)
                .isEqualTo(updatedAuthor);
    }

    @DisplayName("удалять автора")
    @Test
    void deleteAuthor() {
        var author = new AuthorDto("1", "Agatha Christie");
        Mockito.when(authorRepository.existsById(author.getId())).thenReturn(Mono.just(Boolean.TRUE));
        Mockito.when(authorRepository.findById("1")).thenReturn(Mono.just(authorMapper.toEntity(author)));
        Mockito.when(bookRepository.authorExistsInBooks(authorMapper.toEntity(author))).thenReturn(Mono.just(Boolean.FALSE));
        Mockito.when(authorRepository.deleteById(author.getId())).thenReturn(Mono.empty());
        // Delete Author
        this.webClient.delete()
                .uri("/api/author/1")
                .exchange()
                .expectStatus().isOk();
    }
}