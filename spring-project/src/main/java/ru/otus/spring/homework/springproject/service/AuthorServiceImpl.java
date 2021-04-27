package ru.otus.spring.homework.springproject.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.homework.springproject.mappers.AuthorMapper;
import ru.otus.spring.homework.springproject.models.dto.AuthorDto;
import ru.otus.spring.homework.springproject.models.entity.Author;
import ru.otus.spring.homework.springproject.repositories.AuthorRepository;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class AuthorServiceImpl implements AuthorService {

    private static final String AUTHOR_SERVICE = "authorService";

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
    @Secured("ROLE_ADMIN")
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
    @Secured("ROLE_ADMIN")
    public AuthorDto getAuthor(long authorId) {
        log.debug("getAuthor()");
        return authorMapper.toDto(authorRepository.findById(authorId).orElse(null));
    }

    @Override
    @Transactional
    @Secured("ROLE_ADMIN")
    public AuthorDto addAuthor(String authorName) {
        log.debug("addAuthor()");
        return authorMapper.toDto(authorRepository.save(new Author(0, authorName)));
    }

    @Override
    @Transactional
    @Secured("ROLE_ADMIN")
    public AuthorDto updateAuthor(AuthorDto authorDto) {
        log.debug("updateAuthor()");
        return authorMapper.toDto(authorRepository.save(authorMapper.toEntity(authorDto)));
    }

    @Override
    @Transactional
    @Secured("ROLE_ADMIN")
    public void deleteAuthor(long authorId) {
        log.debug("deleteAuthor()");
        authorRepository.deleteById(authorId);
    }

    @Override
    public List<AuthorDto> bulkHeadGetAll(Exception e) {
        log.info("bulkHeadGetAll");
        List<AuthorDto> authors = new ArrayList<>();
        authors.add(new AuthorDto("0", "NoName"));
        return authors;
    }
}
