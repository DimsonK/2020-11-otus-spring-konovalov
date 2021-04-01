package ru.otus.spring.homework.spring16.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;
import ru.otus.spring.homework.spring16.models.dto.AuthorDto;
import ru.otus.spring.homework.spring16.models.dto.CommentDto;
import ru.otus.spring.homework.spring16.service.BookService;
import ru.otus.spring.homework.spring16.service.CommentService;

@Controller
public class CommentController {

    private static final String FROM_COMMENT_LIST = "commentList";
    private static final String FROM_COMMENT_EDIT = "commentEdit";
    private static final String URL_COMMENT_LIST = "/comments/book/%s";

    private final CommentService commentService;
    private final BookService bookService;

    public CommentController(CommentService commentService, BookService bookService) {
        this.commentService = commentService;
        this.bookService = bookService;
    }

    // Получить все комментарии для книги
    @GetMapping("/comments/book/{bookId}")
    public String getComments(@PathVariable("bookId") Long bookId, Model model) {
        var comments = commentService.getCommentsByBookId(bookId);
        var book = bookService.getBook(bookId);
        model.addAttribute("comments", comments);
        model.addAttribute("book", book);
        return CommentController.FROM_COMMENT_LIST;
    }

    // Получить комментарий
    @GetMapping("/comments/{bookId}/{commentId}")
    public String getComment(
            @PathVariable("bookId") String bookId,
            @PathVariable("commentId") Long commentId,
            Model model
    ) {
        CommentDto comment;
        if (commentId.equals(0L)) {
            comment = new CommentDto();
            comment.setId(commentId.toString());
            comment.setBookId(bookId);
        } else {
            comment = commentService.getComment(commentId);
        }
        var book = comment != null ? bookService.getBook(Long.parseLong(comment.getBookId())) : null;
        model.addAttribute("comment", comment);
        model.addAttribute("book", book);
        return CommentController.FROM_COMMENT_EDIT;
    }

    // Добавить комментарий
    @PostMapping("/comments")
    public RedirectView addComment(@ModelAttribute("comment") CommentDto commentDto) {
        CommentDto comment;
        if (commentDto != null && commentDto.getBookId() != null) {
            comment = commentService.addComment(commentDto);
            return new RedirectView(String.format(CommentController.URL_COMMENT_LIST, comment.getBookId()));
        }
        return new RedirectView("/");
    }

    // Обновить комментарий
    @PostMapping("/comments/{commentId}")
    public RedirectView updateComment(
            @PathVariable("commentId") Long commentId,
            @ModelAttribute("comment") CommentDto commentDto
    ) {
        CommentDto comment;
        if (commentDto != null && commentDto.getBookId() != null) {
            if (commentDto.getId() == null) {
                commentDto.setId("0");
                comment = commentService.addComment(commentDto);
            } else {
                comment = commentService.updateComment(commentDto);
            }
            return new RedirectView(String.format(CommentController.URL_COMMENT_LIST, comment.getBookId()));
        }
        return new RedirectView("/");
    }

    @PostMapping("/comments/{id}/remove")
    public RedirectView deleteBook(@PathVariable("id") String commentId) {
        var book = bookService.getBookByCommentId(commentId);
        if (commentId != null) {
            commentService.deleteComment(Long.parseLong(commentId));
        }
        return new RedirectView(String.format(CommentController.URL_COMMENT_LIST, book.getId()));
    }

}
