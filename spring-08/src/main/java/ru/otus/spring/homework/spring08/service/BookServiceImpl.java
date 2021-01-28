package ru.otus.spring.homework.spring08.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.otus.spring.homework.spring08.models.Book;
import ru.otus.spring.homework.spring08.repositories.BookRepository;
import ru.otus.spring.homework.spring08.repositories.CommentRepository;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {
    private static final Logger log = LoggerFactory.getLogger(BookServiceImpl.class);

    private final BookRepository bookRepository;
    private final CommentRepository commentRepository;

    public BookServiceImpl(
            BookRepository bookRepository,
            CommentRepository commentRepository) {
        this.bookRepository = bookRepository;
        this.commentRepository = commentRepository;
    }

    @Override
    public List<Book> getAll() {
        log.debug("getAll()");
        return bookRepository.findAll();
    }

    @Override
    public Book getBook(String bookId) {
        log.debug("getBook()");
        return bookRepository.findById(bookId).orElse(null);
    }

    @Override
    public Book addBook(Book book) {
        log.debug("addBook()");
        return bookRepository.save(book);
    }

    @Override
    public Book updateBook(Book book) {
        log.debug("updateBook()");
        return bookRepository.save(book);
    }

    @Override
    public void deleteBook(String bookId) {
        log.debug("deleteBook()");
        commentRepository.deleteByBookId(bookId);
        bookRepository.deleteById(bookId);
    }

    @Override
    public long getCount() {
        log.debug("getCount()");
        return bookRepository.count();
    }

}
