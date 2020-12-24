package ru.otus.spring.homework.spring05.service;

import ru.otus.spring.homework.spring05.domain.Genre;

public interface GenreService {

    Genre getGenre();

    void addGenre();

    void updateGenre();

    void deleteGenre();

    void printGenres();

}
