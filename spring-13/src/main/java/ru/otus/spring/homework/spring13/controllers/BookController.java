package ru.otus.spring.homework.spring13.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.otus.spring.homework.spring13.models.dto.BookDto;
import ru.otus.spring.homework.spring13.service.BookService;

import java.util.List;
import java.util.Optional;

@RestController
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    // Получить все книги или книги содержащие в названии searchText
    @GetMapping("/api/book")
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
    public ResponseEntity<BookDto> getBook(@PathVariable("id") Long bookId) {
        var book = bookService.getBook(bookId);
        if (book == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(book, HttpStatus.OK);
    }

    // Создать новую книгу
    @PostMapping("/api/book")
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

}
