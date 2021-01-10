package ru.otus.spring.homework.spring06.shell;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.shell.standard.ShellCommandGroup;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.otus.spring.homework.spring06.models.Author;
import ru.otus.spring.homework.spring06.models.Book;
import ru.otus.spring.homework.spring06.models.Genre;
import ru.otus.spring.homework.spring06.service.BookService;

import java.util.List;
import java.util.stream.Collectors;

@ShellComponent
@ShellCommandGroup("Book commands")
public class BookShellCommands {
    private static final Logger log = LoggerFactory.getLogger(BookShellCommands.class);

    private final BookService bookService;
    private final AuthorShellCommands authorShellCommands;
    private final GenreShellCommands genreShellCommands;
    private final ShellReader shellReader;

    public BookShellCommands(
            BookService bookService,
            AuthorShellCommands authorShellCommands,
            GenreShellCommands genreShellCommands,
            ShellReader shellReader
    ) {
        this.bookService = bookService;
        this.authorShellCommands = authorShellCommands;
        this.genreShellCommands = genreShellCommands;
        this.shellReader = shellReader;
    }

    @ShellMethod(value = "Print all books", key = {"bp", "book-print"})
    public void printBooks() {
        log.debug("printBooks()");
        System.out.println("Существующие записи книг в системе:");
        List<Book> bookList = bookService.getAll();
        bookList.forEach(book -> {
            System.out.printf(
                    "%s: name: %s | author: %s | ",
                    book.getId(), book.getName(), book.getAuthor().getName()
            );
            System.out.printf("genres: %s%n", book.getGenres().stream()
                    .map(genre -> String.format("%s (%s)", genre.getName(), genre.getId()))
                    .collect(Collectors.joining(", ")));
        });
        System.out.printf("Total books: %s%n", bookService.getCount());
    }

    @ShellMethod(value = "Print book comments", key = {"bpc", "book-print-comment"})
    public void printBookComments() {
        log.debug("printBookComments()");
        printBooks();
        System.out.println("Выберите книгу для просмотре комментариев:");
        long bookId = Long.parseLong(shellReader.readShell());
        var book = bookService.getBookFull(bookId);
        if (book != null) {
            var comments = book.getComments();
            comments.forEach(comment ->
                    System.out.printf("id: %s, date: %s, author: %s,%n content: %s%n",
                            comment.getId(), comment.getPostDate(), comment.getAuthorName(), comment.getContent()));
        } else {
            System.out.printf("Book with id: %s not found", bookId);
        }
    }

    @ShellMethod(value = "Adding a book", key = {"ba", "book-add"})
    public String addBook() {
        log.debug("addBook()");
        System.out.println("Введите название книги: ");
        String bookName = shellReader.readShell();
        Author author = authorShellCommands.getAuthor();
        List<Genre> genres = genreShellCommands.getGenres();
        var book = bookService.addBook(new Book(0, bookName, author, genres, null));
        return String.format("Книга с id: %s добавлена", book.getId());
    }

    @ShellMethod(value = "Edit a book", key = {"be", "book-edit"})
    public String editBook() {
        log.debug("editBook()");
        printBooks();
        System.out.println("Введите ID книги");
        long bookId = Long.parseLong(shellReader.readShell());
        Book book = bookService.getBookFull(bookId);
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
        printBooks();
        System.out.println("Введите ID книги для удаления: ");
        long bookId = Long.parseLong(shellReader.readShell());
        Book book = bookService.getBook(bookId);
        if (book != null) {
            bookService.deleteBook(book.getId());
        } else {
            System.out.println("Книга не найдена");
        }

        return "Книга удалена";
    }

    public Book getBook() {
        log.debug("getBook()");
        printBooks();
        System.out.println("Выберите ID книги: ");
        long bookId = Long.parseLong(shellReader.readShell());
        return bookService.getBook(bookId);
    }

}
