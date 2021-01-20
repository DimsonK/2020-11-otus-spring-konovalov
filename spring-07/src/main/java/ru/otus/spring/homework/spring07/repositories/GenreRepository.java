package ru.otus.spring.homework.spring07.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.spring.homework.spring07.models.Genre;

public interface GenreRepository extends JpaRepository<Genre, Long> {

}
