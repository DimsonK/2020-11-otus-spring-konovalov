package ru.otus.spring.homework.spring07.service;

import ru.otus.spring.homework.spring07.models.Author;

import java.util.List;

public interface AuthorService {

    List<Author> getAll();

    Author getAuthor(long authorId);

    Author addAuthor(String authorName);

    Author updateAuthor(Author author);

    void deleteAuthor(long authorId);

    long getCount();

}
