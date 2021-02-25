package ru.otus.spring.homework.spring11.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ServerWebInputException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.spring.homework.spring11.mappers.BookMapper;
import ru.otus.spring.homework.spring11.models.dto.BookDto;
import ru.otus.spring.homework.spring11.repositories.BookRepository;
import ru.otus.spring.homework.spring11.repositories.CommentRepository;

import java.util.List;
import java.util.Optional;

@RestController
public class BookController {

    private final BookRepository bookRepository;
    private final BookMapper bookMapper;
    private final CommentRepository commentRepository;

    public BookController(BookRepository bookRepository, BookMapper bookMapper,
            CommentRepository commentRepository) {
        this.bookRepository = bookRepository;
        this.bookMapper = bookMapper;
        this.commentRepository = commentRepository;
    }

    // Получить все книги или книги содержащие в названии searchText
    @GetMapping("/api/book")
    public Flux<BookDto> getBooks(@RequestParam("search") Optional<String> searchText) {
        return searchText.map(s -> bookRepository.findBookByNameContaining(s)
                .flatMap(book ->
                        Mono.just(book)
                                .zipWith(commentRepository.findByBookId(book.getId()).collectList())
                                .map(tuple1 -> bookMapper.toDto(tuple1.getT1(), tuple1.getT2()))))
                .orElseGet(() -> bookRepository.findAll()
                        .flatMap(book ->
                                Mono.just(book)
                                        .zipWith(commentRepository.findByBookId(book.getId()).collectList())
                                        .map(tuple2 -> bookMapper.toDto(tuple2.getT1(), tuple2.getT2()))));
    }

    // Получить книгу по id
    @GetMapping("/api/book/{id}")
    public Mono<ResponseEntity<BookDto>> getBook(@PathVariable("id") String bookId) {
        return bookRepository.findById(bookId)
                .zipWith(commentRepository.findByBookId(bookId).collectList())
                .map(tuple -> bookMapper.toDto(tuple.getT1(), tuple.getT2()))
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    // Создать новую книгу
    @PostMapping("/api/book")
    public Mono<ResponseEntity<BookDto>> addBook(@RequestBody BookDto bookDto) {
        bookDto.setId(null);
        return bookRepository.save(bookMapper.toEntity(bookDto))
                .map(newBook -> ResponseEntity.ok(bookMapper.toDto(newBook, List.of())))
                .defaultIfEmpty(ResponseEntity.badRequest().build());
    }

    // Обновить книгу
    @PutMapping("/api/book/{id}")
    public Mono<ResponseEntity<BookDto>> updateBook(@PathVariable("id") String id, @RequestBody BookDto bookDto) {
        bookDto.setId(id);
        return bookRepository.save(bookMapper.toEntity(bookDto))
                .zipWith(commentRepository.findByBookId(id).collectList())
                .map(tuple -> ResponseEntity.ok(bookMapper.toDto(tuple.getT1(), tuple.getT2())))
                .defaultIfEmpty(ResponseEntity.badRequest().build());
    }

    // Удалить книгу
    @DeleteMapping("/api/book/{id}")
    public Mono<ResponseEntity<Void>> deleteBook(@PathVariable("id") String bookId) {
        return bookRepository.existsById(bookId)
                .doOnNext(exists -> {
                    if (Boolean.FALSE.equals(exists)) {
                        throw new ServerWebInputException("Книга не найдена");
                    }
                })
                .flatMap(exists -> bookRepository.deleteById(bookId))
                .doOnNext(r -> commentRepository.deleteByBookId(bookId))
                .map(r -> ResponseEntity.ok().build());
    }

}
