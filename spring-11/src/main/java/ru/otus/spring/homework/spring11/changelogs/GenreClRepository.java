package ru.otus.spring.homework.spring11.changelogs;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import ru.otus.spring.homework.spring11.models.entity.Genre;

@Repository
public interface GenreClRepository extends MongoRepository<Genre, String> {

}
