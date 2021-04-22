package ru.otus.spring.homework.spring18.controllers;

import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.bulkhead.annotation.Bulkhead.Type;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.otus.spring.homework.spring18.models.dto.AuthorDto;
import ru.otus.spring.homework.spring18.service.AuthorService;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
public class AuthorController {

    private static final String AUTHOR_SERVICE = "authorService";

    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    // Получить всех авторов
    @GetMapping("/api/author")
    @Bulkhead(name = AUTHOR_SERVICE, fallbackMethod = "bulkHeadGetAllAuthor", type = Type.SEMAPHORE)
    public ResponseEntity<List<AuthorDto>> getAuthors() {
        return new ResponseEntity<>(authorService.getAll(), HttpStatus.OK);
    }

    // Получить автора по id
    @GetMapping("/api/author/{id}")
    @Bulkhead(name = AUTHOR_SERVICE, fallbackMethod = "bulkHeadGetAuthor", type = Type.SEMAPHORE)
    public ResponseEntity<AuthorDto> getAuthor(@PathVariable("id") Long authorId) {
        var author = authorService.getAuthor(authorId);
        if (author == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(author, HttpStatus.OK);
    }

    // Создать нового автора
    @PostMapping("/api/author")
    @Bulkhead(name = AUTHOR_SERVICE, fallbackMethod = "bulkHeadGetAuthor", type = Type.SEMAPHORE)
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
    @Bulkhead(name = AUTHOR_SERVICE, fallbackMethod = "bulkHeadGetAuthor", type = Type.SEMAPHORE)
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
    @Bulkhead(name = AUTHOR_SERVICE, fallbackMethod = "bulkHeadDelAuthor", type = Type.SEMAPHORE)
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

    public ResponseEntity<List<AuthorDto>> bulkHeadGetAllAuthor(Exception t) {
        log.info("bulkHeadGetAllAuthor");
        List<AuthorDto> authors = new ArrayList<>();
        authors.add(new AuthorDto("0", "NoName"));
        return new ResponseEntity<>(authors, HttpStatus.OK);
    }

    public ResponseEntity<AuthorDto> bulkHeadGetAuthor(Exception t) {
        log.info("bulkHeadGetAuthor");
        var author = new AuthorDto("0", "NoName");
        return new ResponseEntity<>(author, HttpStatus.OK);
    }

    public ResponseEntity<String> bulkHeadDelAuthor(Exception t) {
        log.info("bulkHeadDelAuthor");
        return new ResponseEntity<>("", HttpStatus.OK);
    }

}
