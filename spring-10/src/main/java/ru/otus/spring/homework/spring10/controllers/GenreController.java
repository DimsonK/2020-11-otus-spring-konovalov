package ru.otus.spring.homework.spring10.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.otus.spring.homework.spring10.models.dto.GenreDto;
import ru.otus.spring.homework.spring10.service.GenreService;

import java.util.List;

@RestController
public class GenreController {

    private final GenreService genreService;

    public GenreController(GenreService genreService) {
        this.genreService = genreService;
    }

    // Получить все жанры
    @GetMapping("/api/genre")
    public ResponseEntity<List<GenreDto>> getGenres() {
        return new ResponseEntity<>(genreService.getAll(), HttpStatus.OK);
    }

    // Получить жанр
    @GetMapping("/api/genre/{id}")
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

}
