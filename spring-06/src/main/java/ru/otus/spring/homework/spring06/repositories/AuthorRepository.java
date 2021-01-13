package ru.otus.spring.homework.spring06.repositories;

import ru.otus.spring.homework.spring06.models.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorRepository {

    Author save(Author author);

    Optional<Author> findById(long id);

    List<Author> findAll();

    Optional<Author> findByName(String name);

    void deleteById(long id);

    long count();
}
