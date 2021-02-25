package ru.otus.spring.homework.spring11.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ServerWebInputException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.spring.homework.spring11.mappers.BookMapper;
import ru.otus.spring.homework.spring11.mappers.CommentMapper;
import ru.otus.spring.homework.spring11.models.dto.CommentDto;
import ru.otus.spring.homework.spring11.repositories.BookRepository;
import ru.otus.spring.homework.spring11.repositories.CommentRepository;

import java.util.List;

@RestController
public class CommentController {

    private final CommentRepository commentRepository;
    private final CommentMapper commentMapper;
    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    public CommentController(
            CommentRepository commentRepository,
            CommentMapper commentMapper,
            BookRepository bookRepository,
            BookMapper bookMapper
    ) {
        this.commentRepository = commentRepository;
        this.commentMapper = commentMapper;
        this.bookRepository = bookRepository;
        this.bookMapper = bookMapper;
    }

    // Получить все комментарии для книги
    @GetMapping("/api/comment/book/{bookId}")
    public Flux<CommentDto> getComments(@PathVariable("bookId") String bookId) {
        return commentRepository.findByBookId(bookId).map(commentMapper::toDto);
    }

    // Получить комментарий
    @GetMapping("/api/comment/{id}")
    public Mono<ResponseEntity<CommentDto>> getComment(@PathVariable("id") String commentId) {
        return commentRepository.findById(commentId).map(commentMapper::toDto)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    // Добавить комментарий
    @PostMapping("/api/comment")
    public Mono<ResponseEntity<CommentDto>> addComment(@RequestBody CommentDto commentDto) {
        commentDto.setId(null);
        return Mono.just(commentDto)
                .zipWith(bookRepository.findById(commentDto.getBookId()).map(book -> bookMapper.toDto(book, List.of())))
                .flatMap(tuple -> commentRepository.save(commentMapper.toEntity(tuple.getT1(), tuple.getT2())))
                .map(newComment -> ResponseEntity.ok(commentMapper.toDto(newComment)))
                .defaultIfEmpty(ResponseEntity.badRequest().build());
    }

    // Обновить комментарий
    @PutMapping("/api/comment/{id}")
    public Mono<ResponseEntity<CommentDto>> updateComment(@PathVariable("id") String id, @RequestBody CommentDto commentDto) {
        commentDto.setId(id);
        return Mono.just(commentDto)
                .zipWith(bookRepository.findById(commentDto.getBookId()).map(book -> bookMapper.toDto(book, List.of())))
                .flatMap(tuple -> commentRepository.save(commentMapper.toEntity(tuple.getT1(), tuple.getT2())))
                .map(newComment -> ResponseEntity.ok(commentMapper.toDto(newComment)))
                .defaultIfEmpty(ResponseEntity.badRequest().build());
    }


    @DeleteMapping("/api/comment/{id}")
    public Mono<ResponseEntity<Void>> deleteComment(@PathVariable("id") String commentId) {
        return commentRepository.existsById(commentId)
                .doOnNext(exists -> {
                    if (Boolean.FALSE.equals(exists)) {
                        throw new ServerWebInputException("Комментарий не найден");
                    }
                })
                .flatMap(exists -> commentRepository.deleteById(commentId))
                .map(r -> ResponseEntity.ok().build());
    }

}
