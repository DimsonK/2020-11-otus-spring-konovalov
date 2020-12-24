package ru.otus.spring.homework.spring05.dao;

import ru.otus.spring.homework.spring05.domain.Book;

import java.util.List;

public interface BookDao {

    long getNextId();

    int count();

    void insert(Book book);

    void update(Book book);

    Book getById(long id);

    List<Book> getAll();

    void deleteById(long id);
}
