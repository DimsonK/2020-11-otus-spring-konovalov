package ru.otus.spring.homework.spring07.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.otus.spring.homework.spring07.models.Author;
import ru.otus.spring.homework.spring07.repositories.AuthorRepository;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DisplayName("Сервис для работы с авторами должен")
@SpringBootTest(classes = AuthorServiceImpl.class)
class AuthorServiceImplTest {

    @MockBean AuthorRepository authorRepository;
    @Autowired AuthorServiceImpl authorService;

    @DisplayName("возвращать автора при вводе его ID в консоль")
    @Test
    void getAuthor() {
        var expectedAuthor = new Author(1, "Agatha Christie");
        Mockito.when(authorRepository.findById(expectedAuthor.getId()))
                .thenReturn(Optional.of(new Author(1, "Agatha Christie")));
        var actualAuthor = authorRepository.findById(expectedAuthor.getId()).orElse(null);
        assertThat(actualAuthor).isNotNull().usingRecursiveComparison().isEqualTo(expectedAuthor);
    }
}