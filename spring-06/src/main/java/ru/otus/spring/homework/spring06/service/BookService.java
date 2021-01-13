package ru.otus.spring.homework.spring06.service;

import ru.otus.spring.homework.spring06.models.Book;

import java.util.List;

public interface BookService {

    List<Book> getAll();

    Book getBook(long bookId);

    Book getBookFull(long bookId);

    Book addBook(Book book);

    Book updateBook(Book book);

    void deleteBook(long bookId);

    long getCount();

}
