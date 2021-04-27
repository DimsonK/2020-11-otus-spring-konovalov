package ru.otus.spring.homework.springproject.controllers;

import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.bulkhead.annotation.Bulkhead.Type;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.otus.spring.homework.springproject.models.dto.CommentDto;
import ru.otus.spring.homework.springproject.service.CommentService;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
public class CommentController {

    private static final String COMMENT_SERVICE = "commentService";

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    // Получить все комментарии для книги
    @GetMapping("/api/comment/book/{bookId}")
    @Bulkhead(name = COMMENT_SERVICE, fallbackMethod = "bulkHeadGetAllComment", type = Type.SEMAPHORE)
    public ResponseEntity<List<CommentDto>> getComments(@PathVariable("bookId") Long bookId) {
        var comments = commentService.getCommentsByBookId(bookId);
        return new ResponseEntity<>(comments, HttpStatus.OK);
    }

    // Получить комментарий
    @GetMapping("/api/comment/{id}")
    @Bulkhead(name = COMMENT_SERVICE, fallbackMethod = "bulkHeadGetComment", type = Type.SEMAPHORE)
    public ResponseEntity<CommentDto> getComment(@PathVariable("id") Long commentId) {
        var comment = commentService.getComment(commentId);
        if (comment == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(comment, HttpStatus.OK);
    }

    // Добавить комментарий
    @PostMapping("/api/comment")
    @Bulkhead(name = COMMENT_SERVICE, fallbackMethod = "bulkHeadGetComment", type = Type.SEMAPHORE)
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
    @Bulkhead(name = COMMENT_SERVICE, fallbackMethod = "bulkHeadGetComment", type = Type.SEMAPHORE)
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
    @Bulkhead(name = COMMENT_SERVICE, fallbackMethod = "bulkHeadDelComment", type = Type.SEMAPHORE)
    public ResponseEntity<String> deleteComment(@PathVariable("id") Long commentId) {
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

    public ResponseEntity<List<CommentDto>> bulkHeadGetAllComment(Exception t) {
        log.info("bulkHeadGetAllComment");
        List<CommentDto> comments = new ArrayList<>();
        comments.add(new CommentDto("1", "10.10.2020", "Вася",
                "Замечательная классическая книга! Написано, как и все остальные рассказы Агаты, очень интересным и доступным языком.",
                true, "1"));
        return new ResponseEntity<>(comments, HttpStatus.OK);
    }

    public ResponseEntity<CommentDto> bulkHeadGetComment(Exception t) {
        log.info("bulkHeadGetComment");
        var comment = new CommentDto("1", "10.10.2020", "Вася",
                "Замечательная классическая книга! Написано, как и все остальные рассказы Агаты, очень интересным и доступным языком.",
                true, "1");
        return new ResponseEntity<>(comment, HttpStatus.OK);
    }

    public ResponseEntity<String> bulkHeadDelComment(Exception t) {
        log.info("bulkHeadDelComment");
        return new ResponseEntity<>("", HttpStatus.OK);
    }

}
