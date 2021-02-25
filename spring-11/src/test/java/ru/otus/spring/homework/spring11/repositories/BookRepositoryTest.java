package ru.otus.spring.homework.spring11.repositories;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import reactor.test.StepVerifier;
import ru.otus.spring.homework.spring11.AbstractRepositoryTest;
import ru.otus.spring.homework.spring11.models.entity.Author;
import ru.otus.spring.homework.spring11.models.entity.Book;
import ru.otus.spring.homework.spring11.models.entity.Genre;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@Slf4j
@DisplayName("Репозиторий для работы с книгами должен")
class BookRepositoryTest extends AbstractRepositoryTest {

    private static final long EXPECTED_BOOKS_COUNT = 5L;

    @Autowired
    BookRepository bookRepository;

    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    @DisplayName("возвращать ожидаемое количество книг в БД")
    @Test
    void shouldReturnExpectedBookCount() {
        var actualBookCount = bookRepository.count().block();
        assertThat(actualBookCount).isEqualTo(EXPECTED_BOOKS_COUNT);
    }

    @DisplayName("добавлять книгу в БД")
    @Test
    void shouldInsertBook() {
        var expectedBook = bookRepository.save(new Book("Детектив",
                new Author("Agatha Christie"), new Genre("Detective"))).block();
        assertNotNull(expectedBook);
        var actualBook = bookRepository.findById(expectedBook.getId());
        StepVerifier
                .create(actualBook)
                .assertNext(book -> {
                    assertNotNull(book.getId());
                    assertThat(book).isNotNull().usingRecursiveComparison().isEqualTo(expectedBook);
                })
                .expectComplete()
                .verify();
    }

    @DisplayName("обновлять книгу в БД")
    @Test
    void shouldUpdateBook() {
        var expectedBook = bookRepository.save(new Book("Детектив",
                new Author("Agatha Christie"), new Genre("Detective"))).block();
        assertNotNull(expectedBook);
        var oldBook = bookRepository.findById(expectedBook.getId()).block();
        assertThat(oldBook).isNotNull();
        expectedBook.setName("Фантастика");
        bookRepository.save(expectedBook).subscribe();
        var actualBook = bookRepository.findById(oldBook.getId());
        StepVerifier
                .create(actualBook)
                .assertNext(book -> {
                    assertNotNull(book);
                    assertThat(book)
                            .isNotNull()
                            .usingRecursiveComparison()
                            .withStrictTypeChecking()
                            .ignoringAllOverriddenEquals()
                            .isNotEqualTo(oldBook);
                    assertThat(book)
                            .isNotNull()
                            .usingRecursiveComparison()
                            .withStrictTypeChecking()
                            .ignoringAllOverriddenEquals()
                            .isEqualTo(expectedBook);
                })
                .expectComplete()
                .verify();
    }

    @DisplayName("возвращать ожидаемую книгу по его id")
    @Test
    void shouldReturnExpectedBookById() {
        var expectedBook = bookRepository.save(new Book("Murder on the Orient Express",
                new Author("Agatha Christie"), new Genre("Detective"))).block();
        assertNotNull(expectedBook);
        var actualBook = bookRepository.findById(expectedBook.getId());
        StepVerifier
                .create(actualBook)
                .assertNext(book -> {
                    assertThat(book)
                            .isNotNull()
                            .usingRecursiveComparison()
                            .isEqualTo(expectedBook);
                })
                .expectComplete()
                .verify();

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

        var actualBookList = bookRepository.findAll().collectList().block();
        assertThat(actualBookList)
                .usingElementComparatorIgnoringFields("id", "author", "genres")
                .containsExactlyInAnyOrderElementsOf(expectedBookList);
    }

    @DisplayName("удалять заданную книгу по ее id")
    @Test
    void shouldCorrectDeleteBookById() {
        var books = bookRepository.findAll().collectList().block();
        assertThat(books).isNotNull();
        assertNotNull(books.get(0));
        var bookId = books.get(0).getId();
        bookRepository.deleteById(bookId).subscribe();
        var actualBook = bookRepository.findById(bookId).block();
        assertThat(actualBook).isNull();
    }
}