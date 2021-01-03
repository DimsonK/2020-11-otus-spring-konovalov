package ru.otus.spring.homework.spring06.service;

import ru.otus.spring.homework.spring06.models.Author;

public interface AuthorService {

    Author getAuthor();

    void addAuthor();

    void updateAuthor();

    void deleteAuthor();

    void printAuthors();

}
