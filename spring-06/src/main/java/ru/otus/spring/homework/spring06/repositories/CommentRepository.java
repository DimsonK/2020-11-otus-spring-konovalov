package ru.otus.spring.homework.spring06.repositories;

import ru.otus.spring.homework.spring06.models.Book;
import ru.otus.spring.homework.spring06.models.Comment;

import java.util.List;
import java.util.Optional;

public interface CommentRepository {

    Comment save(Comment comment);

    Optional<Comment> findById(long id);

    List<Comment> findAll();

    List<Comment> findByBook(Book book);

    List<Comment> findByContent(String name);

    List<Comment> findByAuthor(String authorName);

    void deleteById(long id);

    long count();
}
