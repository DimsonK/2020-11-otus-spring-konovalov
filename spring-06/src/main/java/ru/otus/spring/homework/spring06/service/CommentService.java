package ru.otus.spring.homework.spring06.service;

import ru.otus.spring.homework.spring06.models.Comment;

public interface CommentService {

    Comment getComment(long commentId);

    Comment addComment(Comment comment);

    Comment updateComment(Comment comment);

    void deleteComment(long commentId);

    long getCount();

}
