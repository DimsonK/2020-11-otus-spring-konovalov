package ru.otus.spring.homework.springproject.repositories;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.homework.springproject.models.entity.Author;
import ru.otus.spring.homework.springproject.models.entity.Book;
import ru.otus.spring.homework.springproject.models.entity.Genre;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Репозиторий для работы с книгами должен")
@DataJpaTest
class BookRepositoryTest {

    private static final long EXPECTED_GENRES_COUNT = 9L;

    @Autowired
    TestEntityManager em;

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
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    void shouldInsertBook() {
        var expectedBook = new Book(0L, "Детектив", 12, 2,
            new Author(1L, "Agatha Christie"), List.of(new Genre(1L, "Detective")), null, null);
        expectedBook = bookRepository.save(expectedBook);
        var actualBook = bookRepository.findById(expectedBook.getId()).orElse(null);
        assertThat(actualBook).isNotNull().usingRecursiveComparison().isEqualTo(expectedBook);
    }

    @DisplayName("обновлять книгу в БД")
    @Test
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    void shouldUpdateBook() {
        var expectedBook = new Book(1L, "Детектив", 12, 2,
            new Author(1L, "Agatha Christie"), List.of(new Genre(1L, "Detective")), null, null);
        var oldBook = bookRepository.findById(expectedBook.getId()).orElse(null);
        assertThat(oldBook).isNotNull();
        var genres = oldBook.getGenres().toString();
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
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    void shouldReturnExpectedBookById() {
        var expectedBook = new Book(1L, "Murder on the Orient Express", 12, 2,
            new Author(1L, "Agatha Christie"), List.of(new Genre(1L, "Detective")), new ArrayList<>(), new ArrayList<>());
        var actualBook = bookRepository.findById(expectedBook.getId()).orElse(null);
        assertThat(actualBook)
            .isNotNull()
            .usingRecursiveComparison()
            .ignoringFields(
                "createdAt", "createdBy", "modifiedAt", "modifiedBy",
                "author.createdAt", "author.createdBy", "author.modifiedAt", "author.modifiedBy",
                "genres", "orders", "instances"
            )
            .isEqualTo(expectedBook);
    }

    @DisplayName("возвращать ожидаемый список книг")
    @Test
    void shouldReturnExpectedBooksList() {
        var expectedBookList = List.of(
            new Book(1L, "Murder on the Orient Express", 12, 2,
                new Author(1L, "Agatha Christie"), List.of(new Genre(1L, "Detective")),
                new ArrayList<>(), new ArrayList<>()),
            new Book(2L, "The Three Musketeers", 6, 2,
                new Author(2L, "Alexandre Dumas"), List.of(new Genre(2L, "History")),
                new ArrayList<>(), new ArrayList<>()),
            new Book(3L, "Twenty Thousand Leagues Under the Sea", 6, 2,
                new Author(3L, "Jules Gabriel Verne"), List.of(new Genre(3L, "Fantasy")),
                new ArrayList<>(), new ArrayList<>()),
            new Book(4L, "The Gold Bug", 6, 2,
                new Author(4L, "Edgar Allan Poe"), List.of(new Genre(3L, "Fantasy")),
                new ArrayList<>(), new ArrayList<>()),
            new Book(5L, "It", 16, 2,
                new Author(5L, "Stephen Edwin King"), List.of(new Genre(4L, "Horror")),
                new ArrayList<>(), new ArrayList<>()),
            new Book(6L, "Nine and a Half Weeks: A Memoir of a Love Affair", 18, 2,
                new Author(6L, "Elizabeth McNeill"), List.of(new Genre(2L, "History")),
                new ArrayList<>(), new ArrayList<>()),
            new Book(7L, "Fifty Shades of Grey", 18, 2,
                new Author(7L, "E L James, Zachary Webber, et al."), List.of(new Genre(2L, "History")),
                new ArrayList<>(), new ArrayList<>()),
            new Book(8L, "Радио не для всех. Закрытые частоты Минобороны.", 18, 1,
                new Author(8L, "Попов, Шойгу и Ко"), List.of(new Genre(5L, "Technical")),
                new ArrayList<>(), new ArrayList<>()),
            new Book(9L, "Оценка проходимости танка Армата в плотных слоях атмосферы", 18, 1,
                new Author(9L, "Уралвагонзавод и сотоварищи"), List.of(new Genre(5L, "Technical")),
                new ArrayList<>(), new ArrayList<>())
        );

        var actualBookList = bookRepository.findAll();
        assertThat(actualBookList)
            .usingRecursiveFieldByFieldElementComparator()
            .usingElementComparatorIgnoringFields(
                "createdAt", "createdBy", "modifiedAt", "modifiedBy", "author", "genres", "orders", "instances"
            )
            .containsExactlyInAnyOrderElementsOf(expectedBookList);
    }

    @DisplayName("удалять заданную книгу по ее id")
    @Test
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    void shouldCorrectDeleteBookById() {
        final var bookForDeleteId = 1L;
        bookRepository.deleteById(bookForDeleteId);
        var actualBook = bookRepository.findById(bookForDeleteId).orElse(null);
        assertThat(actualBook).isNull();
    }
}
