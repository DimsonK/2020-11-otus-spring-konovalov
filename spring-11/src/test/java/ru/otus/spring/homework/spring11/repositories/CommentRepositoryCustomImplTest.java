package ru.otus.spring.homework.spring11.repositories;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import ru.otus.spring.homework.spring11.AbstractRepositoryTest;
import ru.otus.spring.homework.spring11.models.entity.Book;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class CommentRepositoryCustomImplTest extends AbstractRepositoryTest {

    private static final int EXPECTED_COMMENTS_COUNT = 2;
    private static final int EXPECTED_FAVORITE_COMMENTS_COUNT = 1;
    Book book;

    @Autowired CommentRepository commentRepository;
    @Autowired BookRepository bookRepository;

    @BeforeEach
    void findBook() {
        var exBook = new Book();
        exBook.setName("Murder on the Orient Express");
        Example<Book> example = Example.of(exBook);
        var expectedBook = bookRepository.findOne(example).block();
        assertThat(expectedBook).isNotNull();
        this.book = expectedBook;
    }

    @AfterEach
    void teardown() {
        book = null;
    }

    @Test
    void findByBookId() {
        var comments = commentRepository.findByBookId(this.book.getId()).collectList().block();
        assertThat(comments).isNotNull();
        var size = comments.size();
        assertThat(size).isEqualTo(EXPECTED_COMMENTS_COUNT);
    }

    @Test
    void findFavoritesByBookId() {
        var comments = commentRepository.findFavoritesByBookId(this.book.getId()).collectList().block();
        assertThat(comments).isNotNull();
        var size = comments.size();
        assertThat(size).isEqualTo(EXPECTED_FAVORITE_COMMENTS_COUNT);
    }

    @Test
    void deleteBiBookId() {
        var comments = commentRepository.findByBookId(this.book.getId()).collectList().block();
        assertThat(comments).isNotNull();
        var size = comments.size();
        assertThat(size).isEqualTo(EXPECTED_COMMENTS_COUNT);

        commentRepository.deleteByBookId(this.book.getId()).subscribe();
        var actualComments = commentRepository.findByBookId(this.book.getId()).collectList().block();
        assertThat(actualComments).isNotNull();
        var actualSize = actualComments.size();
        assertThat(actualSize).isZero();
    }
}