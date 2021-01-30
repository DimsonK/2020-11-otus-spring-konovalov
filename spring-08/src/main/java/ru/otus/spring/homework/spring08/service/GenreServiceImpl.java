package ru.otus.spring.homework.spring08.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.otus.spring.homework.spring08.models.Genre;
import ru.otus.spring.homework.spring08.repositories.BookRepository;
import ru.otus.spring.homework.spring08.repositories.GenreRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class GenreServiceImpl implements GenreService {
    private static final Logger log = LoggerFactory.getLogger(GenreServiceImpl.class);

    private final GenreRepository genreRepository;
    private final BookRepository bookRepository;

    public GenreServiceImpl(
            GenreRepository genreRepository,
            BookRepository bookRepository) {
        this.genreRepository = genreRepository;
        this.bookRepository = bookRepository;
    }

    @Override
    public List<Genre> getAll() {
        log.debug("getAll()");
        return genreRepository.findAll();
    }

    @Override
    public Genre getGenre(String genreId) {
        log.debug("getGenre()");
        return genreRepository.findById(genreId).orElse(null);
    }

    @Override
    public List<Genre> getGenres(List<String> genreIds) {
        log.debug("getGenres()");
        List<Genre> genres = new ArrayList<>();
        for (String item : genreIds) {
            genreRepository.findById(item).ifPresent(genres::add);
        }
        return genres;
    }

    @Override
    public Genre addGenre(String genreName) {
        log.debug("addGenre()");
        return genreRepository.save(new Genre(genreName));
    }

    @Override
    public Genre updateGenre(Genre genre) {
        log.debug("updateGenre()");
        bookRepository.updateGenreInBooks(genre);
        return genreRepository.save(genre);
    }

    @Override
    public void deleteGenre(String genreId) {
        log.debug("deleteGenre()");
        genreRepository.deleteById(genreId);
    }

    @Override
    public boolean isExistsInBooks(Genre genre) {
        log.debug("deleteGenre()");
        return bookRepository.genreExistsInBooks(genre);
    }

    @Override
    public long getCount() {
        log.debug("getCount()");
        return genreRepository.count();
    }

}
