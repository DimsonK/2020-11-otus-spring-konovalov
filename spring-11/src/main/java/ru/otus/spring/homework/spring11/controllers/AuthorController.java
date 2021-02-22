package ru.otus.spring.homework.spring11.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ServerWebInputException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.spring.homework.spring11.mappers.AuthorMapper;
import ru.otus.spring.homework.spring11.models.dto.AuthorDto;
import ru.otus.spring.homework.spring11.repositories.AuthorRepository;
import ru.otus.spring.homework.spring11.repositories.BookRepository;

@RestController
public class AuthorController {

    private final AuthorRepository authorRepository;
    private final AuthorMapper authorMapper;
    private final BookRepository bookRepository;

    public AuthorController(AuthorRepository authorRepository, AuthorMapper authorMapper,
            BookRepository bookRepository) {
        this.authorRepository = authorRepository;
        this.authorMapper = authorMapper;
        this.bookRepository = bookRepository;
    }

    // Получить всех авторов
    @GetMapping("/api/author")
    public Flux<AuthorDto> getAuthors() {
        return authorRepository.findAll().map(authorMapper::toDto);
    }

    // Получить автора по id
    @GetMapping("/api/author/{id}")
    public Mono<ResponseEntity<AuthorDto>> getAuthor(@PathVariable("id") String authorId) {
        return authorRepository.findById(authorId).map(authorMapper::toDto)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    // Создать нового автора
    @PostMapping("/api/author")
    public Mono<ResponseEntity<AuthorDto>> addAuthor(@RequestBody AuthorDto authorDto) {
        authorDto.setId(null);
        return authorRepository.existsAuthorByName(authorDto.getName())
                .doOnNext(exists -> {
                    if (Boolean.TRUE.equals(exists)) {
                        throw new ServerWebInputException("Автор с таким именем уже есть");
                    }
                })
                .flatMap(exists -> authorRepository.save(authorMapper.toEntity(authorDto)))
                .map(newAuthor -> ResponseEntity.ok(authorMapper.toDto(newAuthor)))
                .defaultIfEmpty(ResponseEntity.badRequest().build());
    }

    // Обновить автора
    @PutMapping("/api/author/{id}")
    public Mono<ResponseEntity<AuthorDto>> updateAuthor(@PathVariable("id") String id, @RequestBody AuthorDto authorDto) {
        authorDto.setId(id);
        return authorRepository.existsById(authorDto.getId())
                .doOnNext(exists -> {
                    if (Boolean.FALSE.equals(exists)) {
                        throw new ServerWebInputException("Автор не найден в базе");
                    }
                })
                .flatMap(exists -> authorRepository.save(authorMapper.toEntity(authorDto)))
                .doOnNext(bookRepository::updateAuthorInBooks)
                .map(updatedAuthor -> ResponseEntity.ok(authorMapper.toDto(updatedAuthor)))
                .defaultIfEmpty(ResponseEntity.badRequest().build());
    }

    // Удалить автора
    @DeleteMapping("/api/author/{id}")
    public Mono<ResponseEntity<Void>> deleteAuthor(@PathVariable("id") String authorId) {
        return authorRepository.existsById(authorId)
                .doOnNext(existsAuthor -> {
                    if (Boolean.FALSE.equals(existsAuthor)) {
                        throw new ServerWebInputException("Автор не найден в базе");
                    }
                })
                .zipWith(authorRepository.findById(authorId))
                .flatMap(tuple -> bookRepository.authorExistsInBooks(tuple.getT2()))
                .doOnNext(existsInBooks -> {
                    if (Boolean.TRUE.equals(existsInBooks)) {
                        throw new ServerWebInputException("Автор есть в книгах");
                    }
                })
                .flatMap(author -> authorRepository.deleteById(authorId))
                .map(r -> ResponseEntity.ok().build());
    }

}
