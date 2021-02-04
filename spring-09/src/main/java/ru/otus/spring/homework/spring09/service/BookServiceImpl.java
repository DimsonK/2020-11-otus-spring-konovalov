package ru.otus.spring.homework.spring09.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.homework.spring09.mappers.BookMapper;
import ru.otus.spring.homework.spring09.models.dto.BookDto;
import ru.otus.spring.homework.spring09.repositories.BookRepository;
import ru.otus.spring.homework.spring09.repositories.CommentRepository;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {
    private static final Logger log = LoggerFactory.getLogger(BookServiceImpl.class);

    private final BookRepository bookRepository;
    private final CommentRepository commentRepository;
    private final BookMapper bookMapper;

    public BookServiceImpl(
            BookRepository bookRepository,
            CommentRepository commentRepository, BookMapper bookMapper
    ) {
        this.bookRepository = bookRepository;
        this.commentRepository = commentRepository;
        this.bookMapper = bookMapper;
    }

    @Override
    @Transactional(readOnly = true)
    public List<BookDto> getAll() {
        log.debug("getAll()");
        return bookMapper.toDtoList(bookRepository.findAll());
    }

    @Override
    @Transactional(readOnly = true)
    public BookDto getBook(long bookId) {
        log.debug("getBook()");
        return bookMapper.toDto(bookRepository.findById(bookId).orElse(null));
    }

    @Override
    public List<BookDto> getBooksLikeName(String substring) {
        return bookMapper.toDtoList(bookRepository.findBookByNameLike("%" + substring + "%"));
    }

    @Override
    public BookDto getBookByCommentId(String commentId) {
        var comment = commentRepository.findById(Long.parseLong(commentId));
        return comment.map(value -> bookMapper.toDto(value.getBook())).orElse(null);
    }

    @Override
    @Transactional
    public BookDto addBook(BookDto book) {
        log.debug("addBook()");
        return bookMapper.toDto(bookRepository.save(bookMapper.toEntity(book)));
    }

    @Override
    @Transactional
    public BookDto updateBook(BookDto book) {
        log.debug("updateBook()");
        return bookMapper.toDto(bookRepository.save(bookMapper.toEntity(book)));
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
