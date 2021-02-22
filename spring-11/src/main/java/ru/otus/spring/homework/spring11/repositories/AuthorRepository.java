package ru.otus.spring.homework.spring11.repositories;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;
import ru.otus.spring.homework.spring11.models.entity.Author;

@Repository
public interface AuthorRepository extends ReactiveMongoRepository<Author, String> {

    Mono<Boolean> existsAuthorByName(String name);

}
