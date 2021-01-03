package ru.otus.spring.homework.spring06.repositories;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.homework.spring06.models.Author;
import ru.otus.spring.homework.spring06.models.Book;
import ru.otus.spring.homework.spring06.models.Genre;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Репозиторий для работы с книгами должен")
@DataJpaTest
@Import(BookRepositoryJpa.class)
@Transactional(propagation = Propagation.REQUIRES_NEW)
class BookRepositoryJpaTest {

    private static final int EXPECTED_GENRES_COUNT = 5;

    @Autowired TestEntityManager em;

    @Autowired
    BookRepository bookRepository;

    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    @DisplayName("возвращать ожидаемое количество книг в БД")
    @Test
    void shouldReturnExpectedBookCount() {
        long actualBookCount = bookRepository.count();
        assertThat(actualBookCount).isEqualTo(EXPECTED_GENRES_COUNT);
    }

    @DisplayName("добавлять книгу в БД")
    @Test
    void shouldInsertBook() {
        var expectedBook = new Book(-1, "Детектив",
                new Author(1, "Agatha Christie"), List.of(new Genre(1, "Detective")), null);
        expectedBook = bookRepository.save(expectedBook);
        var actualBook = bookRepository.findById(expectedBook.getId()).orElse(null);
        assertThat(actualBook).isNotNull().usingRecursiveComparison().isEqualTo(expectedBook);
    }

    @DisplayName("обновлять книгу в БД")
    @Test
    void shouldUpdateBook() {
        var expectedBook = new Book(1, "Детектив",
                new Author(1, "Agatha Christie"), List.of(new Genre(1, "Detective")), null);
        var oldBook = bookRepository.findById(expectedBook.getId()).orElse(null);
        assertThat(oldBook).isNotNull();
        em.detach(oldBook);
        bookRepository.save(expectedBook);
        var actualBook = bookRepository.findById(oldBook.getId()).orElse(null);
        assertThat(actualBook)
                .isNotNull()
                .usingRecursiveComparison()
                .ignoringAllOverriddenEquals()
                .isNotEqualTo(oldBook);
        assertThat(actualBook)
                .isNotNull()
                .usingRecursiveComparison()
                .ignoringAllOverriddenEquals()
                .isEqualTo(expectedBook);
    }

    @DisplayName("возвращать ожидаемую книгу по его id")
    @Test
    void shouldReturnExpectedBookById() {
        var expectedBook = new Book(1, "Murder on the Orient Express",
                new Author(1, "Agatha Christie"), List.of(new Genre(1, "Detective")), null);
        var actualBook = bookRepository.findById(expectedBook.getId()).orElse(null);
        assertThat(actualBook).isNotNull();
        actualBook.setComments(null); // ленимся заполнять комментарии
        assertThat(actualBook).isNotNull().usingRecursiveComparison().isEqualTo(expectedBook);
    }

    @DisplayName("возвращать ожидаемый список книг")
    @Test
    void shouldReturnExpectedBooksList() {
        var expectedBookList = List.of(
                new Book(1, "Murder on the Orient Express",
                        new Author(1, "Agatha Christie"), List.of(new Genre(1, "Detective")), null),
                new Book(2, "The Three Musketeers",
                        new Author(2, "Alexandre Dumas"), List.of(new Genre(2, "History")), null),
                new Book(3, "Twenty Thousand Leagues Under the Sea",
                        new Author(3, "Jules Gabriel Verne"), List.of(new Genre(3, "Fantasy")), null),
                new Book(4, "The Gold Bug",
                        new Author(4, "Edgar Allan Poe"), List.of(new Genre(3, "Fantasy")), null),
                new Book(5, "It",
                        new Author(5, "Stephen Edwin King"), List.of(new Genre(4, "Horror")), null));

        var actualBookList = bookRepository.findAll();
        actualBookList.forEach(book -> book.setComments(null));
        assertThat(actualBookList)
                .usingRecursiveFieldByFieldElementComparator()
                .containsExactlyInAnyOrderElementsOf(expectedBookList);
    }

    @DisplayName("удалять заданную книгу по ее id")
    @Test
    void shouldCorrectDeleteBookById() {
        final var bookForDeleteId = 1;
        bookRepository.deleteById(bookForDeleteId);
        var actualBook = bookRepository.findById(bookForDeleteId).orElse(null);
        assertThat(actualBook).isNull();
    }
}