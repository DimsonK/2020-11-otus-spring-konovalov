package ru.otus.spring.homework.spring07.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.homework.spring07.models.Genre;
import ru.otus.spring.homework.spring07.repositories.GenreRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class GenreServiceImpl implements GenreService {
    private static final Logger log = LoggerFactory.getLogger(GenreServiceImpl.class);

    private final GenreRepository genreRepository;

    public GenreServiceImpl(
            GenreRepository genreRepository
    ) {
        this.genreRepository = genreRepository;
    }

    @Override
    public List<Genre> getAll() {
        log.debug("getAll()");
        return genreRepository.findAll();
    }

    @Override
    public Genre getGenre(long genreId) {
        log.debug("getGenre()");
        return genreRepository.findById(genreId).orElse(null);
    }

    @Override
    public List<Genre> getGenres(List<String> genreIds) {
        log.debug("getGenres()");
        List<Genre> genres = new ArrayList<>();
        for (String item : genreIds) {
            genreRepository.findById(Long.parseLong(item)).ifPresent(genres::add);
        }
        return genres;
    }

    @Override
    @Transactional
    public Genre addGenre(String genreName) {
        log.debug("addGenre()");
        return genreRepository.save(new Genre(0, genreName));
    }

    @Override
    @Transactional
    public Genre updateGenre(Genre genre) {
        log.debug("updateGenre()");
        return genreRepository.save(genre);
    }

    @Override
    @Transactional
    public void deleteGenre(long genreId) {
        log.debug("deleteGenre()");
        genreRepository.deleteById(genreId);
    }

    @Override
    public long getCount() {
        log.debug("getCount()");
        return genreRepository.count();
    }

}
