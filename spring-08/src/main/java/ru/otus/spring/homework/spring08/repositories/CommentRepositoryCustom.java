package ru.otus.spring.homework.spring08.repositories;

import ru.otus.spring.homework.spring08.models.Comment;

import java.util.List;

public interface CommentRepositoryCustom {
    List<Comment> findByBookId(String bookId);
    List<Comment> findFavoritesByBookId(String bookId);
    void deleteByBookId(String bookId);
}
