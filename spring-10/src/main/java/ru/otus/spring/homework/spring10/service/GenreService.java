package ru.otus.spring.homework.spring10.service;

import ru.otus.spring.homework.spring10.models.dto.GenreDto;

import java.util.List;

public interface GenreService {

    List<GenreDto> getAll();

    GenreDto getGenre(long genreId);

    List<GenreDto> getGenres(List<String> genreIds);

    GenreDto addGenre(String genreName);

    GenreDto updateGenre(GenreDto genreDto);

    void deleteGenre(long genreId);

    long getCount();

}
