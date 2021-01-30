package ru.otus.spring.homework.spring08.repositories;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import ru.otus.spring.homework.spring08.AbstractRepositoryTest;
import ru.otus.spring.homework.spring08.models.Author;
import ru.otus.spring.homework.spring08.models.Book;
import ru.otus.spring.homework.spring08.models.Genre;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Репозиторий для работы с книгами должен")
class BookRepositoryTest extends AbstractRepositoryTest {
    private static final Logger log = LoggerFactory.getLogger(BookRepositoryTest.class);

    private static final long EXPECTED_BOOKS_COUNT = 5L;

    @Autowired
    BookRepository bookRepository;

    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    @DisplayName("возвращать ожидаемое количество книг в БД")
    @Test
    void shouldReturnExpectedBookCount() {
        long actualBookCount = bookRepository.count();
        assertThat(actualBookCount).isEqualTo(EXPECTED_BOOKS_COUNT);
    }

    @DisplayName("добавлять книгу в БД")
    @Test
    void shouldInsertBook() {
        var expectedBook = bookRepository.save(new Book("Детектив",
                new Author("Agatha Christie"), new Genre("Detective")));
        expectedBook = bookRepository.save(expectedBook);
        var actualBook = bookRepository.findById(expectedBook.getId()).orElse(null);
        assertThat(actualBook).isNotNull().usingRecursiveComparison().isEqualTo(expectedBook);
    }

    @DisplayName("обновлять книгу в БД")
    @Test
    void shouldUpdateBook() {
        var expectedBook = bookRepository.save(new Book("Детектив",
                new Author("Agatha Christie"), new Genre("Detective")));
        var oldBook = bookRepository.findById(expectedBook.getId()).orElse(null);
        assertThat(oldBook).isNotNull();
        expectedBook.setName("Фантастика");
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
        var expectedBook = bookRepository.save(new Book("Murder on the Orient Express",
                new Author("Agatha Christie"), new Genre("Detective")));
        var actualBook = bookRepository.findById(expectedBook.getId()).orElse(null);
        assertThat(actualBook).isNotNull();
        assertThat(actualBook)
                .usingRecursiveComparison()
                .isEqualTo(expectedBook);
    }

    @DisplayName("возвращать ожидаемый список книг")
    @Test
    void shouldReturnExpectedBooksList() {
        var expectedBookList = List.of(
                new Book("Murder on the Orient Express",
                        new Author("Agatha Christie"), new Genre("Detective")),
                new Book("The Three Musketeers",
                        new Author("Alexandre Dumas"), new Genre("History")),
                new Book("Twenty Thousand Leagues Under the Sea",
                        new Author("Jules Gabriel Verne"), new Genre("Fantasy")),
                new Book("The Gold Bug",
                        new Author("Edgar Allan Poe"), new Genre("Fantasy")),
                new Book("It",
                        new Author("Stephen Edwin King"), new Genre("Horror")));

        var actualBookList = bookRepository.findAll();
        assertThat(actualBookList)
                .usingElementComparatorIgnoringFields("id", "author", "genres")
                .containsExactlyInAnyOrderElementsOf(expectedBookList);
    }

    @DisplayName("удалять заданную книгу по ее id")
    @Test
    void shouldCorrectDeleteBookById() {
        var books = bookRepository.findAll();
        assertThat(books).isNotNull();
        var bookId = books.get(0).getId();
        bookRepository.deleteById(bookId);
        var actualBook = bookRepository.findById(bookId).orElse(null);
        assertThat(actualBook).isNull();
    }
}