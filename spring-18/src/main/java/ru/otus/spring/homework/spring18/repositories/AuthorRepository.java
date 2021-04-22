package ru.otus.spring.homework.spring18.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.otus.spring.homework.spring18.models.entity.Author;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {

}
