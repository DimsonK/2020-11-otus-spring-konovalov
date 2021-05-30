package ru.otus.spring.homework.springproject.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.homework.springproject.mappers.BookMapper;
import ru.otus.spring.homework.springproject.models.dto.BookDto;
import ru.otus.spring.homework.springproject.repositories.BookRepository;
import ru.otus.spring.homework.springproject.repositories.CommentRepository;

import java.util.List;

@Slf4j
@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final CommentRepository commentRepository;
    private final CommentService commentService;
    private final BookMapper bookMapper;

    public BookServiceImpl(
        BookRepository bookRepository,
        CommentRepository commentRepository,
        CommentService commentService,
        BookMapper bookMapper) {
        this.bookRepository = bookRepository;
        this.commentRepository = commentRepository;
        this.commentService = commentService;
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
        return bookMapper.toDtoList(bookRepository.findAll(), commentService);
    }

    @Override
    @Transactional(readOnly = true)
    public BookDto getBook(Long bookId) {
        log.debug("getBook()");
        return bookMapper.toDto(bookRepository.findById(bookId).orElse(null), commentService);
    }

    @Override
    @PostFilter("" +
        "(hasAnyRole('ADMIN', 'USER') and filterObject.accessLevel >= authentication.principal.accessLevel and " +
        "filterObject.rars <= authentication.principal.age) or " +
        "(hasRole('ROLE_ANONYMOUS') and filterObject.accessLevel >= 2 and " +
        "filterObject.rars < 18)")
    @Transactional(readOnly = true)
    public List<BookDto> getBooksLikeName(String substring) {
        return bookMapper.toDtoList(bookRepository.findBookByNameContainingIgnoreCase(substring), commentService);
    }

    @Override
    @Transactional(readOnly = true)
    public BookDto getBookByCommentId(String commentId) {
        var comment = commentRepository.findById(Long.parseLong(commentId));
        return comment.map(value -> bookMapper.toDto(value.getBook(), commentService)).orElse(null);
    }

    @Override
    @Transactional
    @Secured("ROLE_ADMIN")
    public BookDto addBook(BookDto book) {
        log.debug("addBook()");
        return bookMapper.toDto(bookRepository.save(bookMapper.toEntity(book)), commentService);
    }

    @Override
    @Transactional
    @Secured("ROLE_ADMIN")
    public BookDto updateBook(BookDto book) {
        log.debug("updateBook()");
        return bookMapper.toDto(bookRepository.save(bookMapper.toEntity(book)), commentService);
    }

    @Override
    @Transactional
    @Secured("ROLE_ADMIN")
    public void deleteBook(Long bookId) {
        log.debug("deleteBook()");
        bookRepository.deleteById(bookId);
    }

    @Override
    @Transactional(readOnly = true)
    public long getCount() {
        log.debug("getCount()");
        return bookRepository.count();
    }

}
