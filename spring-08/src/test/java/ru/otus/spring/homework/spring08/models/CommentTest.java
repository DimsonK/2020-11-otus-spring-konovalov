package ru.otus.spring.homework.spring08.models;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Comment class test")
class CommentTest {

    @DisplayName("class create is correct")
    @Test
    void correctClassCreate() {
        Comment comment = new Comment("01.01.2021", "testAuthorName", "test comment", false, null);
        assertEquals("test comment", comment.getContent());
    }

}
