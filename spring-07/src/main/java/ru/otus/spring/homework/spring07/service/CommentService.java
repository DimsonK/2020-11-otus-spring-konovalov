package ru.otus.spring.homework.spring07.service;

import ru.otus.spring.homework.spring07.models.Comment;

public interface CommentService {

    Comment getComment(long commentId);

    Comment addComment(Comment comment);

    Comment updateComment(Comment comment);

    void deleteComment(long commentId);

    long getCount();

}
