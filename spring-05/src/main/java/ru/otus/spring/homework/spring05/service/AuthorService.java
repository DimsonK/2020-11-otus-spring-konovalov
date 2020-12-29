package ru.otus.spring.homework.spring05.service;

import ru.otus.spring.homework.spring05.domain.Author;

public interface AuthorService {

    Author getAuthor();

    void addAuthor();

    void updateAuthor();

    void deleteAuthor();

    void printAuthors();

}
