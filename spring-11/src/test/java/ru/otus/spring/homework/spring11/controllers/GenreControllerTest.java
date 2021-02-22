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
import ru.otus.spring.homework.spring11.mappers.GenreMapper;
import ru.otus.spring.homework.spring11.models.dto.GenreDto;
import ru.otus.spring.homework.spring11.repositories.BookRepository;
import ru.otus.spring.homework.spring11.repositories.GenreRepository;

import java.util.List;

import static org.mockito.Mockito.doNothing;

@DisplayName("Контроллер жанров должен")
@Slf4j
@WebFluxTest(controllers = GenreController.class)
@Import(GenreMapper.class)
class GenreControllerTest {

    @MockBean
    private GenreRepository genreRepository;
    @MockBean
    private BookRepository bookRepository;

    @Autowired
    private GenreMapper genreMapper;
    @Autowired
    private WebTestClient webClient;

    @DisplayName("возвращать все жанры")
    @Test
    void getGenres() {
        var genres = List.of(
                new GenreDto("1", "Detective"),
                new GenreDto("2", "History"),
                new GenreDto("3", "Fantasy"),
                new GenreDto("4", "Horror")
        );
        Mockito.when(genreRepository.findAll()).thenReturn(Flux.fromIterable(genreMapper.toEntityList(genres)));
        this.webClient.get()
                .uri("/api/genre")
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(GenreDto.class)
                .hasSize(4)
                .isEqualTo(genres);
    }

    @DisplayName("возвращать жанр по его ID")
    @Test
    void testGetGenres() {
        var genre = new GenreDto("1", "Detective");
        Mockito.when(genreRepository.findById("1")).thenReturn(Mono.just(genreMapper.toEntity(genre)));
        this.webClient.get()
                .uri("/api/genre/1")
                .exchange()
                .expectStatus().isOk()
                .expectBody(GenreDto.class)
                .isEqualTo(genre);
    }

    @DisplayName("добавлять жанр")
    @Test
    void addGenre() {
        var newGenre = new GenreDto(null, "Novella");
        var actualGenre = new GenreDto("10", "Novella");
        Mockito.when(genreRepository.existsGenreByName(newGenre.getName())).thenReturn(Mono.just(Boolean.FALSE));
        Mockito.when(genreRepository.save(genreMapper.toEntity(newGenre))).thenReturn(Mono.just(genreMapper.toEntity(actualGenre)));
        this.webClient.post()
                .uri("/api/genre")
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(newGenre))
                .exchange()
                .expectStatus().isOk()
                .expectBody(GenreDto.class)
                .isEqualTo(actualGenre);
    }

    @DisplayName("изменять жанр")
    @Test
    void updateGenre() throws Exception {
        var updatedGenre = new GenreDto("1", "Novella");
        Mockito.when(genreRepository.existsById(updatedGenre.getId())).thenReturn(Mono.just(Boolean.TRUE));
        doNothing().when(bookRepository).updateGenreInBooks(genreMapper.toEntity(updatedGenre));
        Mockito.when(genreRepository.save(genreMapper.toEntity(updatedGenre))).thenReturn(Mono.just(genreMapper.toEntity(updatedGenre)));
        this.webClient.put()
                .uri("/api/genre/1")
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(updatedGenre))
                .exchange()
                .expectStatus().isOk()
                .expectBody(GenreDto.class)
                .isEqualTo(updatedGenre);
    }

    @DisplayName("удалять жанр")
    @Test
    void deleteGenre() throws Exception {
        var genre = new GenreDto("1", "Detective");
        Mockito.when(genreRepository.existsById(genre.getId())).thenReturn(Mono.just(Boolean.TRUE));
        Mockito.when(genreRepository.findById("1")).thenReturn(Mono.just(genreMapper.toEntity(genre)));
        Mockito.when(bookRepository.genreExistsInBooks(genreMapper.toEntity(genre))).thenReturn(Mono.just(Boolean.FALSE));
        Mockito.when(genreRepository.deleteById(genre.getId())).thenReturn(Mono.empty());
        // Delete Author
        this.webClient.delete()
                .uri("/api/genre/1")
                .exchange()
                .expectStatus().isOk();
    }
}