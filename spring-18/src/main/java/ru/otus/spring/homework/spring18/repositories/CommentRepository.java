package ru.otus.spring.homework.spring18.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.otus.spring.homework.spring18.models.entity.Book;
import ru.otus.spring.homework.spring18.models.entity.Comment;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByBook(Book book);

    List<Comment> findByBookAndFavorite(Book book, boolean favorite);
}
