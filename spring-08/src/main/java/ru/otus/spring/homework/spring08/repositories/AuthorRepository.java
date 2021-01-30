package ru.otus.spring.homework.spring08.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import ru.otus.spring.homework.spring08.models.Author;

@Repository
public interface AuthorRepository extends MongoRepository<Author, String> {

}
