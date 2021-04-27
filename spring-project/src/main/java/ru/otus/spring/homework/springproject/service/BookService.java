package ru.otus.spring.homework.springproject.service;

import ru.otus.spring.homework.springproject.models.dto.BookDto;

import java.util.List;

public interface BookService {

    List<BookDto> getAll();

    BookDto getBook(long bookId);

    List<BookDto> getBooksLikeName(String substring);

    BookDto getBookByCommentId(String commentId);

    BookDto addBook(BookDto bookDto);

    BookDto updateBook(BookDto bookDto);

    void deleteBook(long bookId);

    long getCount();

}
