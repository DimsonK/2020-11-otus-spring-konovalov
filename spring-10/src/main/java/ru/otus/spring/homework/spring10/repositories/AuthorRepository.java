package ru.otus.spring.homework.spring10.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.spring.homework.spring10.models.entity.Author;

public interface AuthorRepository extends JpaRepository<Author, Long> {

}
