package ru.otus.spring.homework.springproject.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.homework.springproject.mappers.GenreMapper;
import ru.otus.spring.homework.springproject.models.dto.GenreDto;
import ru.otus.spring.homework.springproject.models.entity.Genre;
import ru.otus.spring.homework.springproject.repositories.GenreRepository;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class GenreServiceImpl implements GenreService {

    private final GenreRepository genreRepository;
    private final GenreMapper genreMapper;

    public GenreServiceImpl(
        GenreRepository genreRepository,
        GenreMapper genreMapper
    ) {
        this.genreRepository = genreRepository;
        this.genreMapper = genreMapper;
    }

    @Override
    @Transactional(readOnly = true)
    @Secured("ROLE_ADMIN")
    public List<GenreDto> getAll() {
        log.debug("getAll()");
        return genreMapper.toDtoList(genreRepository.findAll());
    }

    @Override
    @Transactional(readOnly = true)
    @Secured("ROLE_ADMIN")
    public GenreDto getGenre(Long genreId) {
        log.debug("getGenre()");
        return genreMapper.toDto(genreRepository.findById(genreId).orElse(null));
    }

    @Override
    @Transactional(readOnly = true)
    @Secured("ROLE_ADMIN")
    public List<GenreDto> getGenres(List<String> genreIds) {
        log.debug("getGenres()");
        var ids = genreIds.stream()
            .map(Long::parseLong)
            .collect(Collectors.toList());
        return genreMapper.toDtoList(genreRepository.findAllById(ids));
    }

    @Override
    @Transactional
    @Secured("ROLE_ADMIN")
    public GenreDto addGenre(String genreName) {
        log.debug("addGenre()");
        return genreMapper.toDto(genreRepository.save(new Genre(0L, genreName)));
    }

    @Override
    @Transactional
    @Secured("ROLE_ADMIN")
    public GenreDto updateGenre(GenreDto genreDto) {
        log.debug("updateGenre()");
        return genreMapper.toDto(genreRepository.save(genreMapper.toEntity(genreDto)));
    }

    @Override
    @Transactional
    @Secured("ROLE_ADMIN")
    public void deleteGenre(Long genreId) {
        log.debug("deleteGenre()");
        genreRepository.deleteById(genreId);
    }

    @Override
    public long getCount() {
        log.debug("getCount()");
        return genreRepository.count();
    }

}
