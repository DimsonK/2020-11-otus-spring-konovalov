package ru.otus.spring.homework.spring13.service;

import ru.otus.spring.homework.spring13.models.dto.AuthorDto;

import java.util.List;

public interface AuthorService {

    List<AuthorDto> getAll();

    AuthorDto getAuthor(long authorId);

    AuthorDto addAuthor(String authorName);

    AuthorDto updateAuthor(AuthorDto authorDto);

    void deleteAuthor(long authorId);

    long getCount();

}
