package ru.otus.spring.homework.spring06.service;

import ru.otus.spring.homework.spring06.models.Comment;

public interface CommentService {

    Comment getComment();

    void addComment();

    void updateComment();

    void deleteComment();

    void printComments();
}
