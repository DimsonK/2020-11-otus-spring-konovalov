package ru.otus.spring.homework.spring16.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.spring.homework.spring16.models.entity.Book;
import ru.otus.spring.homework.spring16.models.entity.Comment;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByBook(Book book);

    List<Comment> findByBookAndFavorite(Book book, boolean favorite);
}