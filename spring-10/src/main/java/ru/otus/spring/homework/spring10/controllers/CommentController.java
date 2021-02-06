package ru.otus.spring.homework.spring10.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.otus.spring.homework.spring10.models.dto.CommentDto;
import ru.otus.spring.homework.spring10.service.CommentService;

import java.util.List;

@RestController
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    // Получить все комментарии для книги
    @GetMapping("/api/comment/book/{bookId}")
    public ResponseEntity<List<CommentDto>> getComments(@PathVariable("bookId") Long bookId) {
        var comments = commentService.getCommentsByBookId(bookId);
        return new ResponseEntity<>(comments, HttpStatus.OK);
    }

    // Получить комментарий
    @GetMapping("/api/comment/{id}")
    public ResponseEntity<CommentDto> getComment(@PathVariable("id") Long commentId) {
        var comment = commentService.getComment(commentId);
        if (comment == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(comment, HttpStatus.OK);
    }

    // Добавить комментарий
    @PostMapping("/api/comment")
    public ResponseEntity<CommentDto> addComment(@RequestBody CommentDto commentDto) {
        if (commentDto == null || commentDto.getBookId() == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        commentDto.setId("0");
        var comment = commentService.addComment(commentDto);
        if (comment == null) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(comment, HttpStatus.OK);
    }

    // Обновить комментарий
    @PutMapping("/api/comment/{id}")
    public ResponseEntity<CommentDto> updateComment(@PathVariable("id") Long id, @RequestBody CommentDto commentDto) {
        if (commentDto == null || commentDto.getId() == null || commentDto.getBookId() == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        commentDto.setId(Long.toString(id));
        var comment = commentService.getComment(Long.parseLong(commentDto.getId()));
        if (comment == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        comment = commentService.updateComment(commentDto);
        if (comment == null) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(comment, HttpStatus.OK);
    }


    @DeleteMapping("/api/comment/{id}")
    public ResponseEntity<String> deleteBook(@PathVariable("id") Long commentId) {
        if (commentId == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        var comment = commentService.getComment(commentId);
        if (comment == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        commentService.deleteComment(commentId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
