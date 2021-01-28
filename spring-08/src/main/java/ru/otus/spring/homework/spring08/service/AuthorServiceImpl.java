package ru.otus.spring.homework.spring08.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.otus.spring.homework.spring08.models.Author;
import ru.otus.spring.homework.spring08.repositories.AuthorRepository;
import ru.otus.spring.homework.spring08.repositories.BookRepository;

import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService {
    private static final Logger log = LoggerFactory.getLogger(AuthorServiceImpl.class);

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;

    public AuthorServiceImpl(
            AuthorRepository authorRepository,
            BookRepository bookRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
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
    public Author getAuthor(String authorId) {
        log.debug("getAuthor()");
        return authorRepository.findById(authorId).orElse(null);
    }

    @Override
    public Author addAuthor(String authorName) {
        log.debug("addAuthor()");
        return authorRepository.save(new Author(authorName));
    }

    @Override
    public Author updateAuthor(Author author) {
        log.debug("updateAuthor()");
        bookRepository.updateAuthorInBooks(author);
        return authorRepository.save(author);
    }

    @Override
    public void deleteAuthor(String authorId) {
        log.debug("deleteAuthor()");
        authorRepository.deleteById(authorId);
    }

    @Override
    public boolean isExistsInBook(Author author) {
        log.debug("isExistsInBook()");
        return bookRepository.authorExistsInBooks(author);
    }

}
