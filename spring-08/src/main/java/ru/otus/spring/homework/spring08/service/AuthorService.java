package ru.otus.spring.homework.spring08.service;

import ru.otus.spring.homework.spring08.models.Author;

import java.util.List;

public interface AuthorService {

    List<Author> getAll();

    Author getAuthor(String authorId);

    Author addAuthor(String authorName);

    Author updateAuthor(Author author);

    void deleteAuthor(String authorId);

    boolean isExistsInBook(Author author);

    long getCount();

}
