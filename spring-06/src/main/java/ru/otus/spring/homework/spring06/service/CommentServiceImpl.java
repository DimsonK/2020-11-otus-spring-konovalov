package ru.otus.spring.homework.spring06.service;

import org.springframework.stereotype.Service;
import ru.otus.spring.homework.spring06.models.Comment;
import ru.otus.spring.homework.spring06.repositories.CommentRepository;
import ru.otus.spring.homework.spring06.shell.ShellReader;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class CommentServiceImpl implements CommentService {

    private final ShellReader shellReader;
    private final CommentRepository commentRepository;
    private final BookService bookService;

    public CommentServiceImpl(
            ShellReader shellReader,
            CommentRepository commentRepository,
            BookService bookService
    ) {
        this.shellReader = shellReader;
        this.commentRepository = commentRepository;
        this.bookService = bookService;
    }

    @Override
    public Comment getComment() {
        printComments();
        System.out.println("Выберите ID комментария книги: ");
        long genreId = Long.parseLong(shellReader.readShell());
        return commentRepository.findById(genreId).orElse(null);
    }

    @Override
    public void addComment() {
        var book = bookService.getBook();
        if (book != null) {
            System.out.println("Пожалуйста представьтесь: ");
            var userName = shellReader.readShell();
            System.out.println("Введите комментарий для книги: ");
            var bookComment = shellReader.readShell();
            DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
            String currentDate = df.format(new Date());
            var comment = new Comment(0, currentDate, userName, bookComment, book);
            commentRepository.save(comment);
        } else {
            System.out.println("Книга не найдена");
        }
    }

    @Override
    public void updateComment() {
        printComments();
        System.out.println("Пожалуйста выберите комментарий");
        var commentId = Long.parseLong(shellReader.readShell());
        var comment = commentRepository.findById(commentId).orElse(null);
        if (comment != null) {
            System.out.println("Введите новый комментарий для книги: ");
            var bookComment = shellReader.readShell();
            DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
            String currentDate = df.format(new Date());
            comment.setContent(bookComment);
            comment.setPostDate(currentDate);
            commentRepository.save(comment);
        } else {
            System.out.println("Комментарий не найден");
        }
    }

    @Override
    public void deleteComment() {
        printComments();
        System.out.println("Пожалуйста выберите комментарий");
        var commentId = Long.parseLong(shellReader.readShell());
        var comment = commentRepository.findById(commentId).orElse(null);
        if (comment != null) {
            commentRepository.deleteById(commentId);
        } else {
            System.out.println("Комментарий не найден");
        }
    }

    @Override
    public void printComments() {
        bookService.printBookComments();
    }
}
