package ru.otus.spring.homework.spring10.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.spring.homework.spring10.models.entity.Book;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findBookByNameLike(String substring);

    List<Book> findBookByNameContainingIgnoreCase(String substring);
}
