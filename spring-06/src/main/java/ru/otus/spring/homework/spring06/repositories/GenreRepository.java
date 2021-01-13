package ru.otus.spring.homework.spring06.repositories;

import ru.otus.spring.homework.spring06.models.Genre;

import java.util.List;
import java.util.Optional;

public interface GenreRepository {

    Genre save(Genre genre);

    Optional<Genre> findById(long id);

    List<Genre> findAll();

    Optional<Genre> findByName(String name);

    void deleteById(long id);

    long count();
}
