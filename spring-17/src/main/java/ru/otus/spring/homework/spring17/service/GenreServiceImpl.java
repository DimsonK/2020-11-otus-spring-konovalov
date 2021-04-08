package ru.otus.spring.homework.spring17.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.homework.spring17.mappers.GenreMapper;
import ru.otus.spring.homework.spring17.models.dto.GenreDto;
import ru.otus.spring.homework.spring17.models.entity.Genre;
import ru.otus.spring.homework.spring17.repositories.GenreRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GenreServiceImpl implements GenreService {
    private static final Logger log = LoggerFactory.getLogger(GenreServiceImpl.class);

    private final GenreRepository genreRepository;
    private final GenreMapper genreMapper;

    public GenreServiceImpl(
            GenreRepository genreRepository,
            GenreMapper genreMapper) {
        this.genreRepository = genreRepository;
        this.genreMapper = genreMapper;
    }

    @Override
    @Transactional(readOnly = true)
    @Secured("ADMIN")
    public List<GenreDto> getAll() {
        log.debug("getAll()");
        return genreMapper.toDtoList(genreRepository.findAll());
    }

    @Override
    @Transactional(readOnly = true)
    @Secured("ADMIN")
    public GenreDto getGenre(long genreId) {
        log.debug("getGenre()");
        return genreMapper.toDto(genreRepository.findById(genreId).orElse(null));
    }

    @Override
    @Transactional(readOnly = true)
    @Secured("ADMIN")
    public List<GenreDto> getGenres(List<String> genreIds) {
        log.debug("getGenres()");
        var ids = genreIds.stream()
                .map(Long::parseLong)
                .collect(Collectors.toList());
        return genreMapper.toDtoList(genreRepository.findAllById(ids));
    }

    @Override
    @Transactional
    @Secured("ADMIN")
    public GenreDto addGenre(String genreName) {
        log.debug("addGenre()");
        return genreMapper.toDto(genreRepository.save(new Genre(0, genreName)));
    }

    @Override
    @Transactional
    @Secured("ADMIN")
    public GenreDto updateGenre(GenreDto genreDto) {
        log.debug("updateGenre()");
        return genreMapper.toDto(genreRepository.save(genreMapper.toEntity(genreDto)));
    }

    @Override
    @Transactional
    @Secured("ADMIN")
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
