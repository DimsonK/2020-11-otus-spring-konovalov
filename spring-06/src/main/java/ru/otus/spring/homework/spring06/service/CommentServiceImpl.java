package ru.otus.spring.homework.spring06.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.homework.spring06.models.Book;
import ru.otus.spring.homework.spring06.models.Comment;
import ru.otus.spring.homework.spring06.repositories.CommentRepository;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {
    private static final Logger log = LoggerFactory.getLogger(CommentServiceImpl.class);

    private final CommentRepository commentRepository;

    public CommentServiceImpl(
            CommentRepository commentRepository
    ) {
        this.commentRepository = commentRepository;
    }

    @Override
    public Comment getComment(long commentId) {
        log.debug("getComment()");
        return commentRepository.findById(commentId).orElse(null);
    }

    @Override
    public List<Comment> getCommentsByBook(Book book) {
        log.debug("getCommentsByBook()");
        return commentRepository.findByBook(book);
    }

    @Override
    @Transactional
    public Comment addComment(Comment comment) {
        log.debug("addComment()");
        return commentRepository.save(comment);
    }

    @Override
    @Transactional
    public Comment updateComment(Comment comment) {
        log.debug("updateComment()");
        return commentRepository.save(comment);
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
