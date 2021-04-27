package ru.otus.spring.homework.springproject.controllers;

import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.bulkhead.annotation.Bulkhead.Type;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.otus.spring.homework.springproject.models.dto.GenreDto;
import ru.otus.spring.homework.springproject.service.GenreService;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
public class GenreController {

    private static final String GENRE_SERVICE = "genreService";

    private final GenreService genreService;

    public GenreController(GenreService genreService) {
        this.genreService = genreService;
    }

    // Получить все жанры
    @GetMapping("/api/genre")
    @Bulkhead(name = GENRE_SERVICE, fallbackMethod = "bulkHeadGetAllGenre", type = Type.SEMAPHORE)
    public ResponseEntity<List<GenreDto>> getGenres() {
        return new ResponseEntity<>(genreService.getAll(), HttpStatus.OK);
    }

    // Получить жанр
    @GetMapping("/api/genre/{id}")
    @Bulkhead(name = GENRE_SERVICE, fallbackMethod = "bulkHeadGetGenre", type = Type.SEMAPHORE)
    public ResponseEntity<GenreDto> getGenres(@PathVariable("id") Long genreId) {
        if (genreId == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        GenreDto genre;
        if (genreId.equals(0L)) {
            genre = new GenreDto();
            genre.setId("0");
        } else {
            genre = genreService.getGenre(genreId);
        }
        return new ResponseEntity<>(genre, HttpStatus.OK);
    }

    // Создать новый жанр
    @PostMapping("/api/genre")
    @Bulkhead(name = GENRE_SERVICE, fallbackMethod = "bulkHeadGetGenre", type = Type.SEMAPHORE)
    public ResponseEntity<GenreDto> addGenre(@RequestBody GenreDto genreDto) {
        if (genreDto == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        genreDto.setId("0");
        var genre = genreService.addGenre(genreDto.getName());
        if (genre == null) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(genre, HttpStatus.OK);
    }

    // Обновить жанр
    @PutMapping("/api/genre/{id}")
    @Bulkhead(name = GENRE_SERVICE, fallbackMethod = "bulkHeadGetGenre", type = Type.SEMAPHORE)
    public ResponseEntity<GenreDto> updateGenre(@PathVariable("id") Long id, @RequestBody GenreDto genreDto) {
        if (genreDto == null || genreDto.getId() == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        genreDto.setId(Long.toString(id));
        var genre = genreService.getGenre(Long.parseLong(genreDto.getId()));
        if (genre == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        genre = genreService.updateGenre(genreDto);
        if (genre == null) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(genre, HttpStatus.OK);
    }

    // Удалить жанр
    @DeleteMapping("/api/genre/{id}")
    @Bulkhead(name = GENRE_SERVICE, fallbackMethod = "bulkHeadDelGenre", type = Type.SEMAPHORE)
    public ResponseEntity<String> deleteGenre(@PathVariable("id") Long genreId) {
        if (genreId == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        var genre = genreService.getGenre(genreId);
        if (genre == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        genreService.deleteGenre(genreId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    public ResponseEntity<List<GenreDto>> bulkHeadGetAllGenre(Exception t) {
        log.info("bulkHeadGetAllGenre");
        List<GenreDto> genre = new ArrayList<>();
        genre.add(new GenreDto("1", "Detective"));
        return new ResponseEntity<>(genre, HttpStatus.OK);
    }

    public ResponseEntity<GenreDto> bulkHeadGetGenre(Exception t) {
        log.info("bulkHeadGetGenre");
        var genre = new GenreDto("1", "Detective");
        return new ResponseEntity<>(genre, HttpStatus.OK);
    }

    public ResponseEntity<String> bulkHeadDelGenre(Exception t) {
        log.info("bulkHeadDelGenre");
        return new ResponseEntity<>("", HttpStatus.OK);
    }
}
