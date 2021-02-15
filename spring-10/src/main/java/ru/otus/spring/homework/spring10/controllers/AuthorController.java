package ru.otus.spring.homework.spring10.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.otus.spring.homework.spring10.models.dto.AuthorDto;
import ru.otus.spring.homework.spring10.service.AuthorService;

import java.util.List;

@RestController
public class AuthorController {

    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    // Получить всех авторов
    @GetMapping("/api/author")
    public ResponseEntity<List<AuthorDto>> getAuthors() {
        return new ResponseEntity<>(authorService.getAll(), HttpStatus.OK);
    }

    // Получить автора по id
    @GetMapping("/api/author/{id}")
    public ResponseEntity<AuthorDto> getAuthor(@PathVariable("id") Long authorId) {
        var author = authorService.getAuthor(authorId);
        if (author == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(author, HttpStatus.OK);
    }

    // Создать нового автора
    @PostMapping("/api/author")
    public ResponseEntity<AuthorDto> addAuthor(@RequestBody AuthorDto authorDto) {
        if (authorDto == null || authorDto.getName() == null || authorDto.getName().isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        authorDto.setId("0");
        var author = authorService.addAuthor(authorDto.getName());
        if (author == null) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(author, HttpStatus.OK);
    }

    // Обновить автора
    @PutMapping("/api/author/{id}")
    public ResponseEntity<AuthorDto> updateAuthor(@PathVariable("id") Long id, @RequestBody AuthorDto authorDto) {
        if (authorDto == null || authorDto.getName() == null || authorDto.getName().isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        authorDto.setId(Long.toString(id));
        var author = authorService.getAuthor(Long.parseLong(authorDto.getId()));
        if (author == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        author = authorService.updateAuthor(authorDto);
        if (author == null) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(author, HttpStatus.OK);
    }

    // Удалить автора
    @DeleteMapping("/api/author/{id}")
    public ResponseEntity<String> deleteAuthor(@PathVariable("id") Long authorId) {
        if (authorId == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        var author = authorService.getAuthor(authorId);
        if (author == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        authorService.deleteAuthor(authorId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
