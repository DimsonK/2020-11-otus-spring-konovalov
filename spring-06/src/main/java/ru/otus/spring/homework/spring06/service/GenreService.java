package ru.otus.spring.homework.spring06.service;

import ru.otus.spring.homework.spring06.models.Genre;

import java.util.List;

public interface GenreService {

    Genre getGenre();

    List<Genre> getGenres();

    void addGenre();

    void updateGenre();

    void deleteGenre();

    void printGenres();
}
