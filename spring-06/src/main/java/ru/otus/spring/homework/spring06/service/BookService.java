package ru.otus.spring.homework.spring06.service;

import ru.otus.spring.homework.spring06.models.Book;

public interface BookService {

    Book getBook();

    void addBook();

    void updateBook();

    void deleteBook();

    void printBooks();

    void printBookComments();
}
