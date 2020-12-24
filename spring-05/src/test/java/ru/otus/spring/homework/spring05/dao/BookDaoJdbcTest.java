package ru.otus.spring.homework.spring05.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.homework.spring05.domain.Author;
import ru.otus.spring.homework.spring05.domain.Book;
import ru.otus.spring.homework.spring05.domain.Genre;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayName("Dao для работы с книгами должно")
@JdbcTest
@Import(BookDaoJdbc.class)
@Transactional(propagation = Propagation.REQUIRES_NEW)
class BookDaoJdbcTest {

    private static final int EXPECTED_GENRES_COUNT = 5;

    @MockBean
    AuthorDao authorDao;

    @MockBean
    GenreDao genreDao;

    @Autowired
    BookDao bookDao;

    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    @DisplayName("возвращать ожидаемое количество книг в БД")
    @Test
    void shouldReturnExpectedBookCount() {
        int actualBookCount = bookDao.count();
        assertThat(actualBookCount).isEqualTo(EXPECTED_GENRES_COUNT);
    }

    @DisplayName("добавлять книгу в БД")
    @Test
    void shouldInsertBook() {
        var expectedBook = new Book(10, "Детектив",
                new Author(1, "Agatha Christie"), new Genre(1, "Detective"));
        Mockito.when(authorDao.getById(expectedBook.getAuthor().getId())).thenReturn(new Author(1, "Agatha Christie"));
        Mockito.when(genreDao.getById(expectedBook.getGenre().getId())).thenReturn(new Genre(1, "Detective"));
        bookDao.insert(expectedBook);
        var actualBook = bookDao.getById(expectedBook.getId());
        assertThat(actualBook).isNotNull().usingRecursiveComparison().isEqualTo(expectedBook);
    }

    @DisplayName("обновлять книгу в БД")
    @Test
    void shouldUpdateBook() {
        var expectedBook = new Book(1, "Детектив",
                new Author(1, "Agatha Christie"), new Genre(1, "Detective"));
        var oldBook = bookDao.getById(expectedBook.getId());
        Mockito.when(authorDao.getById(expectedBook.getAuthor().getId())).thenReturn(new Author(1, "Agatha Christie"));
        Mockito.when(genreDao.getById(expectedBook.getGenre().getId())).thenReturn(new Genre(1, "Detective"));
        bookDao.update(expectedBook);
        var actualBook = bookDao.getById(oldBook.getId());
        assertThat(actualBook).usingRecursiveComparison().isNotEqualTo(oldBook);
        assertThat(actualBook).usingRecursiveComparison().isEqualTo(expectedBook);
    }

    @DisplayName("возвращать ожидаемую книгу по его id")
    @Test
    void shouldReturnExpectedBookById() {
        var expectedBook = new Book(1, "Murder on the Orient Express",
                new Author(1, "Agatha Christie"), new Genre(1, "Detective"));
        Mockito.when(authorDao.getById(expectedBook.getAuthor().getId())).thenReturn(new Author(1, "Agatha Christie"));
        Mockito.when(genreDao.getById(expectedBook.getGenre().getId())).thenReturn(new Genre(1, "Detective"));
        var actualBook = bookDao.getById(expectedBook.getId());
        assertThat(actualBook).isNotNull().usingRecursiveComparison().isEqualTo(expectedBook);
    }

    @DisplayName("возвращать ожидаемый список жанров")
    @Test
    void shouldReturnExpectedBooksList() {
        var expectedBookList = List.of(
                new Book(1, "Murder on the Orient Express", null, null),
                new Book(2, "The Three Musketeers", null, null),
                new Book(3, "Twenty Thousand Leagues Under the Sea", null, null),
                new Book(4, "The Gold Bug", null, null),
                new Book(5, "It", null, null));

        var actualBookList = bookDao.getAll();
        assertThat(actualBookList)
                .usingFieldByFieldElementComparator()
                .containsExactlyInAnyOrderElementsOf(expectedBookList);
    }

    @DisplayName("удалять заданную книгу по его id")
    @Test
    void shouldCorrectDeleteBookById() {
        final var bookForDeleteId = 1;
        bookDao.deleteById(bookForDeleteId);
        assertThatThrownBy(() -> bookDao.getById(bookForDeleteId))
                .isInstanceOf(EmptyResultDataAccessException.class);
    }
}