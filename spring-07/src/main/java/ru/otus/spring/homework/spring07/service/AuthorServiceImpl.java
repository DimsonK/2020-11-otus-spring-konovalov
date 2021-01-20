package ru.otus.spring.homework.spring07.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.homework.spring07.models.Author;
import ru.otus.spring.homework.spring07.repositories.AuthorRepository;

import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService {
    private static final Logger log = LoggerFactory.getLogger(AuthorServiceImpl.class);

    private final AuthorRepository authorRepository;

    public AuthorServiceImpl(
            AuthorRepository authorRepository
    ) {
        this.authorRepository = authorRepository;
    }

    @Override
    public List<Author> getAll() {
        log.debug("getAll()");
        return authorRepository.findAll();
    }

    public long getCount() {
        log.debug("getCount()");
        return authorRepository.count();
    }

    @Override
    public Author getAuthor(long authorId) {
        log.debug("getAuthor()");
        return authorRepository.findById(authorId).orElse(null);
    }

    @Override
    @Transactional
    public Author addAuthor(String authorName) {
        log.debug("addAuthor()");
        return authorRepository.save(new Author(0, authorName));
    }

    @Override
    @Transactional
    public Author updateAuthor(Author author) {
        log.debug("updateAuthor()");
        return authorRepository.save(author);
    }

    @Override
    @Transactional
    public void deleteAuthor(long authorId) {
        log.debug("deleteAuthor()");
        authorRepository.deleteById(authorId);
    }

}
