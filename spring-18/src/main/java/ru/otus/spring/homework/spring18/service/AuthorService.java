package ru.otus.spring.homework.spring18.service;

import ru.otus.spring.homework.spring18.models.dto.AuthorDto;

import java.util.List;

public interface AuthorService {

    List<AuthorDto> getAll();

    AuthorDto getAuthor(long authorId);

    AuthorDto addAuthor(String authorName);

    AuthorDto updateAuthor(AuthorDto authorDto);

    void deleteAuthor(long authorId);

    long getCount();

    List<AuthorDto> bulkHeadGetAll(Exception e);

}
