package ru.otus.spring.homework.spring09.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.homework.spring09.mappers.CommentMapper;
import ru.otus.spring.homework.spring09.models.dto.CommentDto;
import ru.otus.spring.homework.spring09.repositories.BookRepository;
import ru.otus.spring.homework.spring09.repositories.CommentRepository;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {
    private static final Logger log = LoggerFactory.getLogger(CommentServiceImpl.class);

    private final CommentRepository commentRepository;
    private final BookRepository bookRepository;
    private final CommentMapper commentMapper;

    public CommentServiceImpl(
            CommentRepository commentRepository,
            BookRepository bookRepository, CommentMapper commentMapper) {
        this.commentRepository = commentRepository;
        this.bookRepository = bookRepository;
        this.commentMapper = commentMapper;
    }

    @Override
    public List<CommentDto> getCommentsByBookId(long bookId) {
        var book = bookRepository.findById(bookId).orElse(null);
        if (book == null) {
            return null;
        }
        return commentMapper.toDtoList(commentRepository.findByBook(book));
    }

    @Override
    public List<CommentDto> getFavoriteCommentsByBookId(long bookId) {
        var book = bookRepository.findById(bookId).orElse(null);
        if (book == null) {
            return null;
        }
        return commentMapper.toDtoList(commentRepository.findByBookAndFavorite(book, true));
    }

    @Override
    @Transactional(readOnly = true)
    public CommentDto getComment(long commentId) {
        log.debug("getComment()");
        return commentMapper.toDto(commentRepository.findById(commentId).orElse(null));
    }

    @Override
    @Transactional
    public CommentDto addComment(CommentDto commentDto) {
        log.debug("addComment()");
        return commentMapper.toDto(commentRepository.save(commentMapper.toEntity(commentDto)));
    }

    @Override
    @Transactional
    public CommentDto updateComment(CommentDto commentDto) {
        log.debug("updateComment()");
        return commentMapper.toDto(commentRepository.save(commentMapper.toEntity(commentDto)));
    }

    @Override
    @Transactional
    public void deleteComment(long commentId) {
        log.debug("deleteComment()");
        commentRepository.deleteById(commentId);
    }

    @Override
    public long getCount() {
        log.debug("getCount()");
        return commentRepository.count();
    }

}
