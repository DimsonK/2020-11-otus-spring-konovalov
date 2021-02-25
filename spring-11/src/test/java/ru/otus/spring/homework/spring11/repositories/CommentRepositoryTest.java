package ru.otus.spring.homework.spring11.repositories;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.test.annotation.DirtiesContext;
import reactor.test.StepVerifier;
import ru.otus.spring.homework.spring11.AbstractRepositoryTest;
import ru.otus.spring.homework.spring11.models.entity.Book;
import ru.otus.spring.homework.spring11.models.entity.Comment;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Репозиторий для работы с комментариями должен")
class CommentRepositoryTest extends AbstractRepositoryTest {

    private static final long EXPECTED_COMMENTS_COUNT = 10L;

    @Autowired CommentRepository commentRepository;
    @Autowired ReactiveMongoTemplate mongoTemplate;

    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    @DisplayName("возвращать ожидаемое количество комментариев в БД")
    @Test
    void shouldReturnExpectedCommentCount() {
        var actualCommentsCount = commentRepository.count();
        StepVerifier
                .create(actualCommentsCount)
                .assertNext(actualAuthorCount -> assertThat(actualAuthorCount).isEqualTo(EXPECTED_COMMENTS_COUNT))
                .expectComplete()
                .verify();
    }

    @DisplayName("добавлять комментарий к книге")
    @Test
    void shouldAddCommentToBook() {
        var query = new Query(Criteria.where("name").is("The Three Musketeers"));
        var book = mongoTemplate.findOne(query, Book.class).block();
        var expectedComment = new Comment("01.01.2020", "TestAuthor", "Test Comment", false, book);
        var savedComment = commentRepository.save(expectedComment);
        StepVerifier
                .create(savedComment)
                .assertNext(comment -> {
                    assertNotNull(comment.getId());
                    assertThat(comment.getBook()).isSameAs(book);
                })
                .expectComplete()
                .verify();
    }

    @DisplayName("возвращать ожидаемые комментарии по книге")
    @Test
    void shouldReturnExpectedCommentById() {
        var query = new Query(Criteria.where("name").is("Murder on the Orient Express"));
        var book = mongoTemplate.findOne(query, Book.class).block();
        assertNotNull(book);
        var comments = commentRepository.findByBookId(book.getId());
        StepVerifier
                .create(comments)
                .assertNext(comment -> {
                    assertNotNull(comment.getId());
                    assertThat(comment.getAuthorName()).isIn(List.of("Вася", "Петя"));
                })
                .assertNext(comment -> {
                    assertNotNull(comment.getId());
                    assertThat(comment.getAuthorName()).isIn(List.of("Вася", "Петя"));
                })
                .expectComplete()
                .verify();
    }

    @DisplayName("удалять заданный комментарий по его id")
    @Test
    void shouldCorrectDeleteCommentById() {
        var query = new Query(Criteria.where("name").is("The Three Musketeers"));
        var book = mongoTemplate.findOne(query, Book.class).block();
        assertNotNull(book);
        var existsComments = commentRepository.findByBookId(book.getId()).collectList().block();
        assertNotNull(existsComments);
        assertNotNull(existsComments.get(0));
        commentRepository.deleteById(existsComments.get(0).getId()).block();
        var expectedComments = commentRepository.findByBookId(book.getId()).collectList().block();
        assertNotNull(expectedComments);
        assertNotEquals(existsComments.size(), expectedComments.size());
        assertTrue(existsComments.size() > expectedComments.size());
    }
}