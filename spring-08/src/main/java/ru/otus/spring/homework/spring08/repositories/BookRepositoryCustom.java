package ru.otus.spring.homework.spring08.repositories;

import ru.otus.spring.homework.spring08.models.Author;
import ru.otus.spring.homework.spring08.models.Genre;

public interface BookRepositoryCustom {

    boolean authorExistsInBooks(Author author);

    boolean genreExistsInBooks(Genre genre);

    void updateAuthorInBooks(Author author);

    void updateGenreInBooks(Genre genre);

}
