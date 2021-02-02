package ru.otus.spring.homework.spring09.repositories;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.homework.spring09.models.entity.Comment;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DisplayName("Репозиторий для работы с комментариями должен")
@DataJpaTest
@Transactional(propagation = Propagation.REQUIRES_NEW)
class CommentRepositoryTest {

    private static final long EXPECTED_COMMENTS_COUNT = 10L;

    @Autowired TestEntityManager em;

    @Autowired CommentRepository commentRepository;

    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    @DisplayName("возвращать ожидаемое количество комментариев в БД")
    @Test
    void shouldReturnExpectedCommentCount() {
        long actualCommentsCount = commentRepository.count();
        assertThat(actualCommentsCount).isEqualTo(EXPECTED_COMMENTS_COUNT);
    }

    @DisplayName("добавлять комментарий к книге")
    @Test
    void shouldAddCommentToBook() {
        var firstComment = commentRepository.findById(1L).orElse(null);
        assertNotNull(firstComment);
        var book = firstComment.getBook();
        var expectedComment = new Comment(20L, "01.01.2020", "TestAuthor", "Test Comment", true, book);
        expectedComment = commentRepository.save(expectedComment);
        var actualComment = commentRepository.findById(expectedComment.getId()).orElse(null);
        assertThat(actualComment).isNotNull().usingRecursiveComparison().isEqualTo(expectedComment);
    }

    @DisplayName("возвращать ожидаемый комментарий по его id")
    @Test
    void shouldReturnExpectedCommentById() {
        var comment = commentRepository.findById(1L).orElse(null);
        assertThat(comment).isNotNull();
        assertEquals("10.10.2020", comment.getPostDate());
    }

    @DisplayName("удалять заданный комментарий по его id")
    @Test
    void shouldCorrectDeleteCommentById() {
        final long commentIdForDelete = 1L;
        commentRepository.deleteById(commentIdForDelete);
        em.flush();
        var actualComment = commentRepository.findById(commentIdForDelete).orElse(null);
        assertThat(actualComment).isNull();
    }
}