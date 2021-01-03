package ru.otus.spring.homework.spring06.shell;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.shell.standard.ShellCommandGroup;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.otus.spring.homework.spring06.service.CommentService;

@ShellComponent
@ShellCommandGroup("Book comment commands")
public class CommentShellCommands {
    private static final Logger log = LoggerFactory.getLogger(CommentShellCommands.class);

    private final CommentService commentService;

    public CommentShellCommands(CommentService commentService) {
        this.commentService = commentService;
    }

    @ShellMethod(value = "Print all comments for book ", key = {"cp", "comment-print"})
    public String printComments() {
        commentService.printComments();
        return "";
    }

    @ShellMethod(value = "Adding a comment for book", key = {"ca", "comment-add"})
    public String addComment() {
        commentService.addComment();
        return "Комментарий добавлен";
    }

    @ShellMethod(value = "Edit a comment for book", key = {"ce", "comment-edit"})
    public String editComment() {
        commentService.updateComment();
        return "Комментарий изменен";
    }

    @ShellMethod(value = "Delete a comment for book", key = {"cd", "comment-delete"})
    public String deleteComment() {
        commentService.deleteComment();
        return "Комментарий удален";
    }
}
