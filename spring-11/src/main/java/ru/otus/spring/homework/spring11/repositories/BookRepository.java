package ru.otus.spring.homework.spring11.repositories;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import ru.otus.spring.homework.spring11.models.entity.Book;

@Repository
public interface BookRepository extends ReactiveMongoRepository<Book, String>, BookRepositoryCustom {

    Flux<Book> findAll();

    Flux<Book> findBookByNameContaining(String searchText);

}
