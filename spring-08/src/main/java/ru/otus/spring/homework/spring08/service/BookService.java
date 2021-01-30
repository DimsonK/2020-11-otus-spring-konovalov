package ru.otus.spring.homework.spring08.service;

import ru.otus.spring.homework.spring08.models.Book;

import java.util.List;

public interface BookService {

    List<Book> getAll();

    Book getBook(String bookId);

    Book addBook(Book book);

    Book updateBook(Book book);

    void deleteBook(String bookId);

    long getCount();

}
