package ru.otus.spring.homework.spring07.shell;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.shell.standard.ShellCommandGroup;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.homework.spring07.models.Comment;
import ru.otus.spring.homework.spring07.service.CommentService;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@ShellComponent
@ShellCommandGroup("Book comment commands")
public class CommentShellCommands {
    private static final Logger log = LoggerFactory.getLogger(CommentShellCommands.class);

    private final CommentService commentService;
    private final ShellReader shellReader;
    private final BookShellCommands bookShellCommands;

    public CommentShellCommands(
            CommentService commentService,
            ShellReader shellReader,
            BookShellCommands bookShellCommands
    ) {
        this.commentService = commentService;
        this.shellReader = shellReader;
        this.bookShellCommands = bookShellCommands;
    }

    @ShellMethod(value = "Print all comments for book ", key = {"cp", "comment-print"})
    public void printComments() {
        log.debug("printComments()");
        bookShellCommands.printBookComments();
    }

    @ShellMethod(value = "Adding a comment for book", key = {"ca", "comment-add"})
    @Transactional
    public String addComment() {
        log.debug("addComment()");
        var book = bookShellCommands.getBook();
        if (book != null) {
            System.out.println("Пожалуйста представьтесь: ");
            var userName = shellReader.readShell();
            System.out.println("Введите комментарий для книги: ");
            var bookComment = shellReader.readShell();
            DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
            String currentDate = df.format(new Date());
            var comment = new Comment(0, currentDate, userName, bookComment, book);
            commentService.addComment(comment);
        } else {
            System.out.println("Книга не найдена");
        }
        return "Комментарий добавлен";
    }

    @ShellMethod(value = "Edit a comment for book", key = {"ce", "comment-edit"})
    public String editComment() {
        log.debug("editComment()");
        printComments();
        System.out.println("Пожалуйста выберите комментарий");
        var commentId = Long.parseLong(shellReader.readShell());
        var comment = commentService.getComment(commentId);
        if (comment != null) {
            System.out.println("Введите новый комментарий для книги: ");
            var bookComment = shellReader.readShell();
            DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
            String currentDate = df.format(new Date());
            comment.setContent(bookComment);
            comment.setPostDate(currentDate);
            commentService.updateComment(comment);
        } else {
            System.out.println("Комментарий не найден");
        }
        return "Комментарий изменен";
    }

    @ShellMethod(value = "Delete a comment for book", key = {"cd", "comment-delete"})
    public String deleteComment() {
        log.debug("deleteComment()");
        printComments();
        System.out.println("Пожалуйста выберите комментарий");
        var commentId = Long.parseLong(shellReader.readShell());
        var comment = commentService.getComment(commentId);
        if (comment != null) {
            commentService.deleteComment(comment.getId());
        } else {
            System.out.println("Комментарий не найден");
        }
        return "Комментарий удален";
    }

    public Comment getComment() {
        log.debug("getComment()");
        printComments();
        System.out.println("Выберите ID комментария книги: ");
        long commentId = Long.parseLong(shellReader.readShell());
        return commentService.getComment(commentId);
    }
}
