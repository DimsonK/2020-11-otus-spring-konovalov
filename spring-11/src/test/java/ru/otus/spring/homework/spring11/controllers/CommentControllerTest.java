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
import ru.otus.spring.homework.spring11.models.dto.CommentDto;
import ru.otus.spring.homework.spring11.models.dto.GenreDto;
import ru.otus.spring.homework.spring11.repositories.BookRepository;
import ru.otus.spring.homework.spring11.repositories.CommentRepository;

import java.util.List;

@DisplayName("Контроллер комментариев должен")
@Slf4j
@WebFluxTest(controllers = CommentController.class)
@Import({BookMapper.class, AuthorMapper.class, GenreMapper.class, CommentMapper.class})
class CommentControllerTest {

    private static final BookDto bookDto = new BookDto("1", "Murder on the Orient Express",
            new AuthorDto("1", "Agatha Christie"), List.of(new GenreDto("1", "Detective")), List.of());

    @MockBean
    private CommentRepository commentRepository;
    @MockBean
    private BookRepository bookRepository;

    @Autowired
    private CommentMapper commentMapper;
    @Autowired
    private BookMapper bookMapper;
    @Autowired
    private AuthorMapper authorMapper;
    @Autowired
    private GenreMapper genreMapper;
    @Autowired
    private WebTestClient webClient;

    @DisplayName("возвращать все комментарии для книги")
    @Test
    void getComments() {
        var comments = List.of(
                new CommentDto("1", "01.01.2020", "Author1", "Comment content 1", true, "1"),
                new CommentDto("2", "02.02.2020", "Author2", "Comment content 2", false, "1")
        );
        Mockito.when(commentRepository.findByBookId("1")).thenReturn(Flux.fromIterable(commentMapper.toEntityList(comments, bookDto)));
        this.webClient.get()
                .uri("/api/comment/book/1")
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(CommentDto.class)
                .hasSize(2)
                .isEqualTo(comments);
    }

    @DisplayName("возвращать комментарий")
    @Test
    void getComment() {
        var comment = new CommentDto("2", "02.02.2020", "Author2", "Comment content 2", false, "1");
        Mockito.when(commentRepository.findById("2")).thenReturn(Mono.just(commentMapper.toEntity(comment, bookDto)));
        this.webClient.get()
                .uri("/api/comment/2")
                .exchange()
                .expectStatus().isOk()
                .expectBody(CommentDto.class)
                .isEqualTo(comment);
    }

    @DisplayName("добавлять комментарий для книги")
    @Test
    void addComment() {
        var newComment = new CommentDto(null, "05.05.2021", "Author1", "Comment content 3", true, "1");
        var actualComment = new CommentDto("1", "05.05.2021", "Author1", "Comment content 3", true, "1");
        Mockito.when(bookRepository.findById(bookDto.getId())).thenReturn(Mono.just(bookMapper.toEntity(bookDto)));
        Mockito.when(commentRepository.save(commentMapper.toEntity(newComment, bookDto))).thenReturn(Mono.just(commentMapper.toEntity(actualComment, bookDto)));
        this.webClient.post()
                .uri("/api/comment")
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(newComment))
                .exchange()
                .expectStatus().isOk()
                .expectBody(CommentDto.class)
                .isEqualTo(actualComment);
    }

    @DisplayName("изменять комментарий")
    @Test
    void updateComment() {
        var updatedComment = new CommentDto("1", "05.05.2021", "Author1", "Comment content 3", true, "1");
        Mockito.when(bookRepository.findById(bookDto.getId())).thenReturn(Mono.just(bookMapper.toEntity(bookDto)));
        Mockito.when(commentRepository.save(commentMapper.toEntity(updatedComment, bookDto))).thenReturn(Mono.just(commentMapper.toEntity(updatedComment, bookDto)));
        this.webClient.put()
                .uri("/api/comment/1")
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(updatedComment))
                .exchange()
                .expectStatus().isOk()
                .expectBody(CommentDto.class)
                .isEqualTo(updatedComment);
    }

    @DisplayName("удалять комментарий")
    @Test
    void deleteComment() {
        var actualComment = new CommentDto("1", "05.05.2021", "Author1", "Comment content 3", true, "1");
        Mockito.when(commentRepository.existsById(actualComment.getId())).thenReturn(Mono.just(Boolean.TRUE));
        Mockito.when(commentRepository.deleteById(actualComment.getId())).thenReturn(Mono.empty());
        this.webClient.delete()
                .uri("/api/comment/1")
                .exchange()
                .expectStatus().isOk();
    }
}