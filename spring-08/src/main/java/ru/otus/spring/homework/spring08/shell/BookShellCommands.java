package ru.otus.spring.homework.spring08.shell;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.shell.standard.ShellCommandGroup;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.otus.spring.homework.spring08.models.Author;
import ru.otus.spring.homework.spring08.models.Book;
import ru.otus.spring.homework.spring08.models.Genre;
import ru.otus.spring.homework.spring08.service.BookService;
import ru.otus.spring.homework.spring08.service.CommentService;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@ShellComponent
@ShellCommandGroup("Book commands")
public class BookShellCommands {
    private static final Logger log = LoggerFactory.getLogger(BookShellCommands.class);

    private final BookService bookService;
    private final AuthorShellCommands authorShellCommands;
    private final GenreShellCommands genreShellCommands;
    private final CommentService commentService;
    private final ShellReader shellReader;

    public BookShellCommands(
            BookService bookService,
            AuthorShellCommands authorShellCommands,
            GenreShellCommands genreShellCommands,
            CommentService commentService,
            ShellReader shellReader
    ) {
        this.bookService = bookService;
        this.authorShellCommands = authorShellCommands;
        this.genreShellCommands = genreShellCommands;
        this.commentService = commentService;
        this.shellReader = shellReader;
    }

    @ShellMethod(value = "Print all books", key = {"bp", "book-print"})
    public void printBooks() {
        log.debug("printBooks()");
        System.out.println("Существующие записи книг в системе:");
        List<Book> bookList = bookService.getAll();
        showBookList(bookList);
        System.out.printf("Total books: %s%n", bookService.getCount());
    }

    @ShellMethod(value = "Print book favorite comments", key = {"bpc", "book-print-comment"})
    public void printBookComments() {
        log.debug("printBookComments()");
        System.out.println("Выберите книгу для просмотре вск комментариев:");
        var book = getBook();
        if (book != null) {
            System.out.println("Хотите посмотреть все (a) комментарии или только избранные (f)? (a/F)");
            var commentType = shellReader.readShell();
            var comments = (commentType.toLowerCase(Locale.ROOT).equals("a")) ?
                    commentService.getCommentsByBookId(book.getId()) :
                    commentService.getFavoriteCommentsByBookId(book.getId());
            if (comments != null) {
                comments.forEach(comment ->
                        System.out.printf("date: %s, author: %s,%n content: %s%n",
                                comment.getPostDate(), comment.getAuthorName(), comment.getContent()));
            }
        } else {
            System.out.println("Book with not found");
        }
    }

    @ShellMethod(value = "Adding a book", key = {"ba", "book-add"})
    public String addBook() {
        log.debug("addBook()");
        System.out.println("Введите название книги: ");
        String bookName = shellReader.readShell();
        Author author = authorShellCommands.getAuthor();
        var genres = genreShellCommands.getGenres();
        var book = bookService.addBook(new Book(bookName, author, genres.toArray(new Genre[0])));
        return String.format("Книга с id: %s добавлена", book.getId());
    }

    @ShellMethod(value = "Edit a book", key = {"be", "book-edit"})
    public String editBook() {
        log.debug("editBook()");
        Book book = getBook();
        if (book != null) {
            System.out.printf("Выбрана книга: id: %s, name: %s%n", book.getId(), book.getName());
            System.out.println("Введите новое название книги: ");
            String bookName = shellReader.readShell();
            Author author = authorShellCommands.getAuthor();
            List<Genre> genres = genreShellCommands.getGenres();
            book.setName(bookName);
            book.setAuthor(author);
            book.setGenres(genres);
            bookService.updateBook(book);
        } else {
            System.out.println("Книга не найдена");
        }
        assert book != null;
        return String.format("Книга c id: %s изменена", book.getId());
    }

    @ShellMethod(value = "Delete a book", key = {"bd", "book-delete"})
    public String deleteBook() {
        log.debug("deleteBook()");
        Book book = getBook();
        if (book != null) {
            bookService.deleteBook(book.getId());
        } else {
            System.out.println("Книга не найдена");
        }
        return "Книга удалена";
    }

    public Book getBook() {
        log.debug("getBook()");
        List<Book> bookList = bookService.getAll();
        showBookList(bookList);
        System.out.println("Выберите номер книги: ");
        var bookNumber = Integer.parseInt(shellReader.readShell());
        return bookList.get(bookNumber) != null ? bookList.get(bookNumber) : null;
    }

    private void showBookList(List<Book> bookList) {
        int[] idx = {0};
        bookList.forEach(book -> {
            var author = book.getAuthor() != null ? book.getAuthor().getName() : "";
            System.out.printf(
                    "%s: name: %s | author: %s | ",
                    idx[0]++, book.getName(), author
            );
            System.out.printf("genres: %s%n", book.getGenres().stream()
                    .map(genre -> String.format("%s", genre.getName()))
                    .collect(Collectors.joining(", ")));
        });
    }
}
