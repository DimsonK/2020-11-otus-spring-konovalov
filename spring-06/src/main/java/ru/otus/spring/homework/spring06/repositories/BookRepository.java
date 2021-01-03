package ru.otus.spring.homework.spring06.repositories;

import ru.otus.spring.homework.spring06.models.Book;

import java.util.List;
import java.util.Optional;

public interface BookRepository {

    Book save(Book book);

    Optional<Book> findById(long id);

    List<Book> findAll();

    Optional<Book> findByName(String name);

    void updateNameById(long id, String name);

    void deleteById(long id);

    long getNextId();

    long count();

}
