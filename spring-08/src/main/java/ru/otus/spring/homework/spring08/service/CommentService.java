package ru.otus.spring.homework.spring08.service;

import ru.otus.spring.homework.spring08.models.Comment;

import java.util.List;

public interface CommentService {

    Comment getComment(String commentId);

    List<Comment> getCommentsByBookId(String bookId);

    List<Comment> getFavoriteCommentsByBookId(String bookId);

    Comment addComment(Comment comment);

    Comment updateComment(Comment comment);

    void deleteComment(String commentId);

    long getCount();

}
