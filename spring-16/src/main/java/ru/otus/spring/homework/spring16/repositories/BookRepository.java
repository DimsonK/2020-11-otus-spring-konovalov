package ru.otus.spring.homework.spring16.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.spring.homework.spring16.models.entity.Book;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findBookByNameLike(String substring);
}
