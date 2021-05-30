package ru.otus.spring.homework.springproject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.otus.spring.homework.springproject.models.entity.Book;
import ru.otus.spring.homework.springproject.models.entity.BookFile;

@Repository
public interface BookFileRepository extends JpaRepository<BookFile, Long> {
    BookFile findBookFileByBook(Book book);
}
