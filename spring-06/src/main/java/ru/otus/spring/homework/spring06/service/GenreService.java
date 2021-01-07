package ru.otus.spring.homework.spring06.service;

import ru.otus.spring.homework.spring06.models.Genre;

import java.util.List;

public interface GenreService {

    List<Genre> getAll();

    Genre getGenre(long genreId);

    List<Genre> getGenres(List<String> genreIds);

    Genre addGenre(String genreName);

    Genre updateGenre(Genre genre);

    void deleteGenre(long genreId);

    long getCount();

}
