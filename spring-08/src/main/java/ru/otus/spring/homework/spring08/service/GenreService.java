package ru.otus.spring.homework.spring08.service;

import ru.otus.spring.homework.spring08.models.Genre;

import java.util.List;

public interface GenreService {

    List<Genre> getAll();

    Genre getGenre(String genreId);

    List<Genre> getGenres(List<String> genreIds);

    Genre addGenre(String genreName);

    Genre updateGenre(Genre genre);

    void deleteGenre(String genreId);

    boolean isExistsInBooks(Genre genre);

    long getCount();

}
