package ru.otus.spring.homework.spring06.repositories;

import ru.otus.spring.homework.spring06.models.Book;

import java.util.List;
import java.util.Optional;

public interface BookRepository {

    Book save(Book book);

    Optional<Book> findById(long bookId);

    Optional<Book> findWithGraph(long bookId, String graphName);

    List<Book> findAll();

    void deleteById(long id);

    long count();

}
