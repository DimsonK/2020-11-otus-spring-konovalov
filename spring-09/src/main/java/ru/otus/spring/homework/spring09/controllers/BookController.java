package ru.otus.spring.homework.spring09.controllers;

import lombok.extern.java.Log;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;
import ru.otus.spring.homework.spring09.models.dto.BookDto;
import ru.otus.spring.homework.spring09.models.dto.GenreDto;
import ru.otus.spring.homework.spring09.service.AuthorService;
import ru.otus.spring.homework.spring09.service.BookService;
import ru.otus.spring.homework.spring09.service.GenreService;

import java.util.List;
import java.util.Optional;

@Log
@Controller
public class BookController {

    private static final String URL_BOOK_LIST = "/books";

    private final BookService bookService;
    private final AuthorService authorService;
    private final GenreService genreService;

    public BookController(
            BookService bookService,
            AuthorService authorService,
            GenreService genreService
    ) {
        this.bookService = bookService;
        this.authorService = authorService;
        this.genreService = genreService;
    }

    @GetMapping("/books")
    public String getBooks(
            @RequestParam("all") Optional<String> getAll,
            @RequestParam("search") Optional<String> searchText,
            @RequestParam("commentsView") Optional<String> commentsView, Model model) {
        List<BookDto> books;
//        if (getAll.isPresent() && Boolean.TRUE.equals(getAll.get())) {
//            bookDtoList = bookService.getAll();
//        } else if (searchText.isPresent()) {
//            bookDtoList = bookService.getBooksLikeName(searchText.get());
//        }
        if (commentsView.isPresent() && commentsView.get().equals("true")) {
            model.addAttribute("commentsView", true);
        }
        books = bookService.getAll();
        model.addAttribute("books", books);
        return "bookList";
    }

    @GetMapping("/books/{id}")
    public String getBook(@PathVariable("id") Long bookId, Model model) {
        var authorList = authorService.getAll();
        var genreList = genreService.getAll();
        BookDto book;
        if (bookId.equals(0L)) {
            book = new BookDto();
            book.setId("0");
            book.setGenres(List.of(new GenreDto()));
        } else {
            book = bookService.getBook(bookId);
        }
        model.addAttribute("book", book);
        model.addAttribute("authorList", authorList);
        model.addAttribute("genreList", genreList);
        log.info(genreList.toString());
        return "bookEdit";
    }

    @PostMapping("/books")
    public RedirectView addBook(@ModelAttribute("book") BookDto bookDto) {
        if (bookDto != null) {
            bookService.addBook(bookDto);
        }
        return new RedirectView(BookController.URL_BOOK_LIST);
    }

    @PostMapping("/books/{id}")
    public RedirectView updateBook(
            @PathVariable("id") Long bookId,
            @ModelAttribute("book") BookDto bookDto
    ) {
        if (bookDto != null) {
            bookService.updateBook(bookDto);
        }
        return new RedirectView(BookController.URL_BOOK_LIST);
    }

    @PostMapping("/books/{id}/remove")
    public RedirectView deleteBook(@PathVariable("id") String bookId) {
        if (bookId != null) {
            bookService.deleteBook(Long.parseLong(bookId));
        }
        return new RedirectView(BookController.URL_BOOK_LIST);
    }

}
