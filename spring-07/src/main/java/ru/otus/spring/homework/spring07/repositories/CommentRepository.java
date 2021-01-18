package ru.otus.spring.homework.spring07.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.spring.homework.spring07.models.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {

}
