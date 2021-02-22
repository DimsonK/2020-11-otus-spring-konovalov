package ru.otus.spring.homework.spring11.repositories;

import reactor.core.publisher.Mono;
import ru.otus.spring.homework.spring11.models.entity.Author;
import ru.otus.spring.homework.spring11.models.entity.Genre;

public interface BookRepositoryCustom {

    Mono<Boolean> authorExistsInBooks(Author author);

    Mono<Boolean> genreExistsInBooks(Genre genre);

    void updateAuthorInBooks(Author author);

    void updateGenreInBooks(Genre genre);

}
