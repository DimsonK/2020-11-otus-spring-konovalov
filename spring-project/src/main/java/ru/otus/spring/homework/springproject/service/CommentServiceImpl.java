package ru.otus.spring.homework.springproject.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.homework.springproject.mappers.CommentMapper;
import ru.otus.spring.homework.springproject.models.dto.CommentDto;
import ru.otus.spring.homework.springproject.repositories.BookRepository;
import ru.otus.spring.homework.springproject.repositories.CommentRepository;

import java.util.Collections;
import java.util.List;

@Slf4j
@Service
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final BookRepository bookRepository;
    private final CommentMapper commentMapper;

    public CommentServiceImpl(
        CommentRepository commentRepository,
        BookRepository bookRepository, CommentMapper commentMapper
    ) {
        this.commentRepository = commentRepository;
        this.bookRepository = bookRepository;
        this.commentMapper = commentMapper;
    }

    @Override
    @Transactional(readOnly = true)
    public List<CommentDto> getCommentsByBookId(Long bookId) {
        var book = bookRepository.findById(bookId).orElse(null);
        if (book == null) {
            return Collections.emptyList();
        }
        return commentMapper.toDtoList(commentRepository.findByBook(book));
    }

    @Override
    @Transactional(readOnly = true)
    public List<CommentDto> getFavoriteCommentsByBookId(Long bookId) {
        var book = bookRepository.findById(bookId).orElse(null);
        if (book == null) {
            return Collections.emptyList();
        }
        return commentMapper.toDtoList(commentRepository.findByBookAndFavorite(book, true));
    }

    @Override
    @Transactional(readOnly = true)
    public CommentDto getComment(Long commentId) {
        log.debug("getComment()");
        return commentMapper.toDto(commentRepository.findById(commentId).orElse(null));
    }

    @Override
    @Transactional
    public CommentDto addComment(CommentDto commentDto) {
        log.debug("addComment()");
        return commentMapper.toDto(commentRepository.save(commentMapper.toEntity(commentDto, bookRepository)));
    }

    @Override
    @Transactional
    @Secured("ROLE_ADMIN")
    public CommentDto updateComment(CommentDto commentDto) {
        log.debug("updateComment()");
        return commentMapper.toDto(commentRepository.save(commentMapper.toEntity(commentDto, bookRepository)));
    }

    @Override
    @Transactional
    @Secured("ROLE_ADMIN")
    public void deleteComment(Long commentId) {
        log.debug("deleteComment()");
        commentRepository.deleteById(commentId);
    }

    @Override
    @Transactional(readOnly = true)
    public long getCount() {
        log.debug("getCount()");
        return commentRepository.count();
    }

}
