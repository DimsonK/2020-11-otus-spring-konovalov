package ru.otus.spring.homework.spring06.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.homework.spring06.models.Book;
import ru.otus.spring.homework.spring06.repositories.BookRepository;
import ru.otus.spring.homework.spring06.repositories.CommentRepository;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {
    private static final Logger log = LoggerFactory.getLogger(BookServiceImpl.class);

    private final BookRepository bookRepository;
    private final CommentRepository commentRepository;

    public BookServiceImpl(
            BookRepository bookRepository,
            CommentRepository commentRepository
    ) {
        this.bookRepository = bookRepository;
        this.commentRepository = commentRepository;
    }

    @Override
    public List<Book> getAll() {
        log.debug("getAll()");
        return bookRepository.findAll();
    }

    @Override
    public Book getBook(long bookId) {
        log.debug("getBook()");
        return bookRepository.findById(bookId).orElse(null);
    }

    @Override
    @Transactional(readOnly = true)
    public Book getBookFull(long bookId) {
        var book = bookRepository.findByIdFull(bookId).orElse(null);
        var comments = commentRepository.findByBook(book);
        if (book != null) {
            book.setComments(comments);
        }
        return book;
    }

    @Override
    @Transactional
    public Book addBook(Book book) {
        log.debug("addBook()");
        return bookRepository.save(book);
    }

    @Override
    @Transactional
    public Book updateBook(Book book) {
        log.debug("updateBook()");
        return bookRepository.save(book);
    }

    @Override
    @Transactional
    public void deleteBook(long bookId) {
        log.debug("deleteBook()");
        bookRepository.deleteById(bookId);
    }

    @Override
    public long getCount() {
        log.debug("getCount()");
        return bookRepository.count();
    }

}
