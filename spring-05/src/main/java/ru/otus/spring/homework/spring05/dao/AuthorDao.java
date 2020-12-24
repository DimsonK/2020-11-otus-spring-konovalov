package ru.otus.spring.homework.spring05.dao;

import ru.otus.spring.homework.spring05.domain.Author;

import java.util.List;

public interface AuthorDao {

    long getNextId();

    int count();

    void insert(Author author);

    void update(Author author);

    Author getById(long id);

    List<Author> getAll();

    void deleteById(long id);
}
