package ru.otus.spring.homework.spring09.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.spring.homework.spring09.models.entity.Genre;

public interface GenreRepository extends JpaRepository<Genre, Long> {

}
