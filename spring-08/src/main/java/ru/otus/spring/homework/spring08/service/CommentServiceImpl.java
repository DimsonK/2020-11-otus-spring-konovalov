package ru.otus.spring.homework.spring08.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.otus.spring.homework.spring08.models.Comment;
import ru.otus.spring.homework.spring08.repositories.CommentRepository;

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
    public Comment getComment(String commentId) {
        log.debug("getComment()");
        return commentRepository.findById(commentId).orElse(null);
    }

    @Override
    public List<Comment> getCommentsByBookId(String bookId) {
        return commentRepository.findByBookId(bookId);
    }

    @Override
    public List<Comment> getFavoriteCommentsByBookId(String bookId) {
        return commentRepository.findFavoritesByBookId(bookId);
    }

    @Override
    public Comment addComment(Comment comment) {
        log.debug("addComment()");
        return commentRepository.save(comment);
    }

    @Override
    public Comment updateComment(Comment comment) {
        log.debug("updateComment()");
        return commentRepository.save(comment);
    }

    @Override
    public void deleteComment(String commentId) {
        log.debug("deleteComment()");
        commentRepository.deleteById(commentId);
    }

    @Override
    public long getCount() {
        log.debug("getCount()");
        return commentRepository.count();
    }

}
