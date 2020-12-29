package ru.otus.spring.homework.spring05.dao;

import ru.otus.spring.homework.spring05.domain.Genre;

import java.util.List;

public interface GenreDao {

    long getNextId();

    int count();

    void insert(Genre genre);

    void update(Genre genre);

    Genre getById(long id);

    List<Genre> getAll();

    void deleteById(long id);
}
