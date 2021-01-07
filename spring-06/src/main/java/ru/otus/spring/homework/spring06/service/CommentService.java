package ru.otus.spring.homework.spring06.service;

import ru.otus.spring.homework.spring06.models.Book;
import ru.otus.spring.homework.spring06.models.Comment;

import java.util.List;

public interface CommentService {

    Comment getComment(long commentId);

    List<Comment> getCommentsByBook(Book book);

    Comment addComment(Comment comment);

    Comment updateComment(Comment comment);

    void deleteComment(long commentId);

    long getCount();

}
