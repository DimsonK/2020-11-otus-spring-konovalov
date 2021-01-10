package ru.otus.spring.homework.spring06.repositories;

import ru.otus.spring.homework.spring06.models.Comment;

import java.util.List;
import java.util.Optional;

public interface CommentRepository {

    Comment save(Comment comment);

    Optional<Comment> findById(long id);

    List<Comment> findAll();

    void deleteById(long id);

    long count();
}
