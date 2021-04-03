package ru.otus.spring.homework.spring16.models;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.otus.spring.homework.spring16.models.entity.Book;
import ru.otus.spring.homework.spring16.models.entity.Comment;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Comment class test")
class CommentTest {

    @DisplayName("class create is correct")
    @Test
    void correctClassCreate() {
        Comment comment = new Comment(10, "10.10.2020", "SomeMan", "SomeComment", true, new Book());
        assertEquals(10, comment.getId());
        assertEquals("SomeMan", comment.getAuthorName());
    }
}
