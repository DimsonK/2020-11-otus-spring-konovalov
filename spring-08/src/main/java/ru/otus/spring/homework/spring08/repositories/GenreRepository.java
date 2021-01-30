package ru.otus.spring.homework.spring08.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import ru.otus.spring.homework.spring08.models.Genre;

@Repository
public interface GenreRepository extends MongoRepository<Genre, String> {

}
