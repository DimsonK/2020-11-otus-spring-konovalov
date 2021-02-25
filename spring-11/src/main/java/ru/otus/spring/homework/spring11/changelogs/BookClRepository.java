package ru.otus.spring.homework.spring11.changelogs;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import ru.otus.spring.homework.spring11.models.entity.Book;

@Repository
public interface BookClRepository extends MongoRepository<Book, String> {

}
