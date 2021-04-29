package ru.otus.spring.homework.springproject.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.homework.springproject.mappers.BookMapper;
import ru.otus.spring.homework.springproject.models.dto.BookDto;
import ru.otus.spring.homework.springproject.repositories.BookRepository;
import ru.otus.spring.homework.springproject.repositories.CommentRepository;

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
    @PostFilter("" +
            "(hasAnyRole('ADMIN', 'USER') and filterObject.accessLevel >= authentication.principal.accessLevel and " +
            "filterObject.rars <= authentication.principal.age) or " +
            "(hasRole('ROLE_ANONYMOUS') and filterObject.accessLevel >= 2 and " +
            "filterObject.rars < 18)")
    public List<BookDto> getAll() {
        log.debug("getAll()");
        return bookMapper.toDtoList(bookRepository.findAll());
    }

    @Override
    @Transactional(readOnly = true)
    public BookDto getBook(Long bookId) {
        log.debug("getBook()");
        return bookMapper.toDto(bookRepository.findById(bookId).orElse(null));
    }

    @Override
    @PostFilter("" +
            "(hasAnyRole('ADMIN', 'USER') and filterObject.accessLevel >= authentication.principal.accessLevel and " +
            "filterObject.rars <= authentication.principal.age) or " +
            "(hasRole('ROLE_ANONYMOUS') and filterObject.accessLevel >= 2 and " +
            "filterObject.rars < 18)")
    @Transactional(readOnly = true)
    public List<BookDto> getBooksLikeName(String substring) {
        return bookMapper.toDtoList(bookRepository.findBookByNameContainingIgnoreCase(substring));
    }

    @Override
    @Transactional(readOnly = true)
    public BookDto getBookByCommentId(String commentId) {
        var comment = commentRepository.findById(Long.parseLong(commentId));
        return comment.map(value -> bookMapper.toDto(value.getBook())).orElse(null);
    }

    @Override
    @Transactional
    @Secured("ROLE_ADMIN")
    public BookDto addBook(BookDto book) {
        log.debug("addBook()");
        return bookMapper.toDto(bookRepository.save(bookMapper.toEntity(book)));
    }

    @Override
    @Transactional
    @Secured("ROLE_ADMIN")
    public BookDto updateBook(BookDto book) {
        log.debug("updateBook()");
        return bookMapper.toDto(bookRepository.save(bookMapper.toEntity(book)));
    }

    @Override
    @Transactional
    @Secured("ROLE_ADMIN")
    public void deleteBook(Long bookId) {
        log.debug("deleteBook()");
        bookRepository.deleteById(bookId);
    }

    @Override
    public long getCount() {
        log.debug("getCount()");
        return bookRepository.count();
    }

}
