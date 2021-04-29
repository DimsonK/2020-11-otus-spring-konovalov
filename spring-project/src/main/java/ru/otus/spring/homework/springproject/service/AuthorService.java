package ru.otus.spring.homework.springproject.service;

import ru.otus.spring.homework.springproject.models.dto.AuthorDto;

import java.util.List;

public interface AuthorService {

    List<AuthorDto> getAll();

    AuthorDto getAuthor(Long authorId);

    AuthorDto addAuthor(String authorName);

    AuthorDto updateAuthor(AuthorDto authorDto);

    void deleteAuthor(Long authorId);

    long getCount();

    List<AuthorDto> bulkHeadGetAll(Exception e);

}
