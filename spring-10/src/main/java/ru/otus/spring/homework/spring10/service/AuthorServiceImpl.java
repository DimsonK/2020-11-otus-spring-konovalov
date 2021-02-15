package ru.otus.spring.homework.spring10.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.homework.spring10.exceptions.BadRequestException;
import ru.otus.spring.homework.spring10.mappers.AuthorMapper;
import ru.otus.spring.homework.spring10.models.dto.AuthorDto;
import ru.otus.spring.homework.spring10.models.entity.Author;
import ru.otus.spring.homework.spring10.repositories.AuthorRepository;

import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService {
    private static final Logger log = LoggerFactory.getLogger(AuthorServiceImpl.class);

    private final AuthorRepository authorRepository;
    private final AuthorMapper authorMapper;

    public AuthorServiceImpl(
            AuthorRepository authorRepository,
            AuthorMapper authorMapper) {
        this.authorRepository = authorRepository;
        this.authorMapper = authorMapper;
    }

    @Override
    @Transactional(readOnly = true)
    public List<AuthorDto> getAll() {
        log.debug("getAll()");
        return authorMapper.toDtoList(authorRepository.findAll());
    }

    public long getCount() {
        log.debug("getCount()");
        return authorRepository.count();
    }

    @Override
    @Transactional(readOnly = true)
    public AuthorDto getAuthor(long authorId) {
        log.debug("getAuthor()");
        return authorMapper.toDto(authorRepository.findById(authorId).orElse(null));
    }

    @Override
    @Transactional
    public AuthorDto addAuthor(String authorName) {
        log.debug("addAuthor()");
        return authorMapper.toDto(authorRepository.save(new Author(0, authorName)));
    }

    @Override
    @Transactional
    public AuthorDto updateAuthor(AuthorDto authorDto) {
        log.debug("updateAuthor()");
        return authorMapper.toDto(authorRepository.save(authorMapper.toEntity(authorDto)));
    }

    @Override
    @Transactional
    public void deleteAuthor(long authorId) {
        log.debug("deleteAuthor()");
        authorRepository.deleteById(authorId);
    }

}
