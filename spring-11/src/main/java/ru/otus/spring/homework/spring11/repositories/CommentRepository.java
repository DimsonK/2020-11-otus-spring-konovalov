package ru.otus.spring.homework.spring11.repositories;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import ru.otus.spring.homework.spring11.models.entity.Comment;

@Repository
public interface CommentRepository extends ReactiveMongoRepository<Comment, String>, CommentRepositoryCustom {

}
