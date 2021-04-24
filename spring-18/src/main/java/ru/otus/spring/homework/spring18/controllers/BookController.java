package ru.otus.spring.homework.spring18.controllers;

import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.bulkhead.annotation.Bulkhead.Type;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.otus.spring.homework.spring18.models.dto.AuthorDto;
import ru.otus.spring.homework.spring18.models.dto.BookDto;
import ru.otus.spring.homework.spring18.models.dto.CommentDto;
import ru.otus.spring.homework.spring18.models.dto.GenreDto;
import ru.otus.spring.homework.spring18.service.BookService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
public class BookController {

    private static final String BOOK_SERVICE = "bookService";

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    // Получить все книги или книги содержащие в названии searchText
    @GetMapping("/api/book")
    @Bulkhead(name = BOOK_SERVICE, fallbackMethod = "bulkHeadGetAllBook", type = Type.SEMAPHORE)
    public ResponseEntity<List<BookDto>> getBooks(@RequestParam("search") Optional<String> searchText) {
        List<BookDto> books;
        if (searchText.isPresent()) {
            books = bookService.getBooksLikeName(searchText.get());
        } else {
            books = bookService.getAll();
        }
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    // Получить книгу по id
    @GetMapping("/api/book/{id}")
    @Bulkhead(name = BOOK_SERVICE, fallbackMethod = "bulkHeadGetBook", type = Type.SEMAPHORE)
    public ResponseEntity<BookDto> getBook(@PathVariable("id") Long bookId) {
        var book = bookService.getBook(bookId);
        if (book == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(book, HttpStatus.OK);
    }

    // Создать новую книгу
    @PostMapping("/api/book")
    @Bulkhead(name = BOOK_SERVICE, fallbackMethod = "bulkHeadGetBook", type = Type.SEMAPHORE)
    public ResponseEntity<BookDto> addBook(@RequestBody BookDto bookDto) {
        if (bookDto == null || bookDto.getName() == null || bookDto.getName().isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        bookDto.setId("0");
        var book = bookService.addBook(bookDto);
        if (book == null) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(book, HttpStatus.OK);
    }

    // Обновить книгу
    @PutMapping("/api/book/{id}")
    @Bulkhead(name = BOOK_SERVICE, fallbackMethod = "bulkHeadGetBook", type = Type.SEMAPHORE)
    public ResponseEntity<BookDto> updateBook(@PathVariable("id") Long id, @RequestBody BookDto bookDto) {
        if (bookDto == null || bookDto.getId() == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        bookDto.setId(Long.toString(id));
        var book = bookService.getBook(Long.parseLong(bookDto.getId()));
        if (book == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        book = bookService.updateBook(bookDto);
        if (book == null) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(book, HttpStatus.OK);
    }

    // Удалить книгу
    @DeleteMapping("/api/book/{id}")
    @Bulkhead(name = BOOK_SERVICE, fallbackMethod = "bulkHeadDelBook", type = Type.SEMAPHORE)
    public ResponseEntity<String> deleteBook(@PathVariable("id") Long bookId) {
        if (bookId == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        var book = bookService.getBook(bookId);
        if (book == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        bookService.deleteBook(bookId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    public ResponseEntity<List<BookDto>> bulkHeadGetAllBook(Exception t) {
        log.info("bulkHeadGetAllBook");
        List<BookDto> books = new ArrayList<>();
        books.add(new BookDto(
                "999", "Murder on the Orient Express", 12, 2,
                new AuthorDto("1", "Agatha Christie"),
                List.of(new GenreDto("1", "Detective")),
                List.of(new CommentDto())));
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    public ResponseEntity<BookDto> bulkHeadGetBook(Exception t) {
        log.info("bulkHeadGetBook");
        var book = new BookDto(
                "999", "Murder on the Orient Express", 12, 2,
                new AuthorDto("1", "Agatha Christie"),
                List.of(new GenreDto("1", "Detective")),
                List.of(new CommentDto()));
        return new ResponseEntity<>(book, HttpStatus.OK);
    }

    public ResponseEntity<String> bulkHeadDelBook(Exception t) {
        log.info("bulkHeadDelBook");
        return new ResponseEntity<>("", HttpStatus.OK);
    }
}
