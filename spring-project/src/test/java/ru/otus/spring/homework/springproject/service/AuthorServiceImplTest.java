package ru.otus.spring.homework.springproject.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.otus.spring.homework.springproject.mappers.AuthorMapper;
import ru.otus.spring.homework.springproject.mappers.AuthorMapperImpl;
import ru.otus.spring.homework.springproject.models.entity.Author;
import ru.otus.spring.homework.springproject.repositories.AuthorRepository;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DisplayName("Сервис для работы с авторами должен")
@SpringBootTest(classes = {AuthorMapper.class, AuthorServiceImpl.class, AuthorMapperImpl.class})
@ExtendWith(MockitoExtension.class)
class AuthorServiceImplTest {

    @MockBean AuthorRepository authorRepository;
    @Autowired AuthorMapper authorMapper;
    @Autowired AuthorService authorService;

    @DisplayName("возвращать автора при вводе его ID в консоль")
    @Test
    void getAuthor() {
        var expectedAuthor = authorMapper.toDto(new Author(1L, "Agatha Christie"));
        Mockito.when(authorRepository.findById(Long.valueOf(expectedAuthor.getId())))
                .thenReturn(Optional.of(new Author(1L, "Agatha Christie")));
        var actualAuthor = authorService.getAuthor(Long.parseLong(expectedAuthor.getId()));
        assertThat(actualAuthor).isNotNull().usingRecursiveComparison().isEqualTo(expectedAuthor);
    }
}
