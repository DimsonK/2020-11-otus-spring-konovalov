package ru.otus.spring.homework.spring08.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import ru.otus.spring.homework.spring08.models.Book;

@Repository
public interface BookRepository extends MongoRepository<Book, String>, BookRepositoryCustom {

}
