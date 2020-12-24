package ru.otus.spring.homework.spring05.service;

import ru.otus.spring.homework.spring05.domain.Book;

public interface BookService {

    Book getBook();

    void addBook();

    void updateBook();

    void deleteBook();

    void printBooks();
}
