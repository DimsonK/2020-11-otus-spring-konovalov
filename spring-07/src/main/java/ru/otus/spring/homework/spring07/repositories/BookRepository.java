package ru.otus.spring.homework.spring07.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.spring.homework.spring07.models.Book;

public interface BookRepository extends JpaRepository<Book, Long> {

}
