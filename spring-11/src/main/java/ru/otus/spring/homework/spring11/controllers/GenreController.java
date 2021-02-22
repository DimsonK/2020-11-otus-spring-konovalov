package ru.otus.spring.homework.spring11.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ServerWebInputException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.spring.homework.spring11.mappers.GenreMapper;
import ru.otus.spring.homework.spring11.models.dto.GenreDto;
import ru.otus.spring.homework.spring11.repositories.BookRepository;
import ru.otus.spring.homework.spring11.repositories.GenreRepository;

@RestController
public class GenreController {

    private final GenreRepository genreRepository;
    private final GenreMapper genreMapper;
    private final BookRepository bookRepository;

    public GenreController(GenreRepository genreRepository, GenreMapper genreMapper,
            BookRepository bookRepository) {
        this.genreRepository = genreRepository;
        this.genreMapper = genreMapper;
        this.bookRepository = bookRepository;
    }

    // Получить все жанры
    @GetMapping("/api/genre")
    public Flux<GenreDto> getGenres() {
        return genreRepository.findAll().map(genreMapper::toDto);
    }

    // Получить жанр
    @GetMapping("/api/genre/{id}")
    public Mono<ResponseEntity<GenreDto>> getGenres(@PathVariable("id") String genreId) {
        return genreRepository.findById(genreId).map(genreMapper::toDto)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    // Создать новый жанр
    @PostMapping("/api/genre")
    public Mono<ResponseEntity<GenreDto>> addGenre(@RequestBody GenreDto genreDto) {
        genreDto.setId(null);
        return genreRepository.existsGenreByName(genreDto.getName())
                .doOnNext(exists -> {
                    if (Boolean.TRUE.equals(exists)) {
                        throw new ServerWebInputException("Жанр с таким названием уже есть");
                    }
                })
                .flatMap(exists -> genreRepository.save(genreMapper.toEntity(genreDto)))
                .map(newGenre -> ResponseEntity.ok(genreMapper.toDto(newGenre)))
                .defaultIfEmpty(ResponseEntity.badRequest().build());
    }

    // Обновить жанр
    @PutMapping("/api/genre/{id}")
    public Mono<ResponseEntity<GenreDto>> updateGenre(@PathVariable("id") String id, @RequestBody GenreDto genreDto) {
        genreDto.setId(id);
        return genreRepository.existsById(genreDto.getId())
                .doOnNext(exists -> {
                    if (Boolean.FALSE.equals(exists)) {
                        throw new ServerWebInputException("Жанр не найден в базе");
                    }
                })
                .flatMap(exists -> genreRepository.save(genreMapper.toEntity(genreDto)))
                .doOnNext(bookRepository::updateGenreInBooks)
                .map(updatedGenre -> ResponseEntity.ok(genreMapper.toDto(updatedGenre)))
                .defaultIfEmpty(ResponseEntity.badRequest().build());
    }

    // Удалить жанр
    @DeleteMapping("/api/genre/{id}")
    public Mono<ResponseEntity<Void>> deleteGenre(@PathVariable("id") String genreId) {
        return genreRepository.existsById(genreId)
                .doOnNext(existsGenre -> {
                    if (Boolean.FALSE.equals(existsGenre)) {
                        throw new ServerWebInputException("Жанр не найден в базе");
                    }
                })
                .zipWith(genreRepository.findById(genreId))
                .flatMap(tuple -> bookRepository.genreExistsInBooks(tuple.getT2()))
                .doOnNext(existsInBooks -> {
                    if (Boolean.TRUE.equals(existsInBooks)) {
                        throw new ServerWebInputException("Жанр есть в книгах");
                    }
                })
                .flatMap(author -> genreRepository.deleteById(genreId))
                .map(r -> ResponseEntity.ok().build());
    }

}
