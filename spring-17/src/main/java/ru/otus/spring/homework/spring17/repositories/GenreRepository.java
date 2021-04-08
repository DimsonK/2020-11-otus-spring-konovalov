package ru.otus.spring.homework.spring17.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.otus.spring.homework.spring17.models.entity.Genre;

@Repository
public interface GenreRepository extends JpaRepository<Genre, Long> {
}
