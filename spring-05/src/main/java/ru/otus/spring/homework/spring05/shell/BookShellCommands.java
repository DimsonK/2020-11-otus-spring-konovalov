package ru.otus.spring.homework.spring05.shell;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.shell.standard.ShellCommandGroup;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.otus.spring.homework.spring05.service.BookService;

@ShellComponent
@ShellCommandGroup("Book commands")
public class BookShellCommands {
    private static final Logger log = LoggerFactory.getLogger(BookShellCommands.class);

    private final BookService bookService;

    public BookShellCommands(BookService bookService) {
        this.bookService = bookService;
    }


    @ShellMethod(value = "Print all books", key = {"bp", "book-print"})
    public String printBooks() {
        bookService.printBooks();
        return "";
    }

    @ShellMethod(value = "Adding a book", key = {"ba", "book-add"})
    public String addBook() {
        bookService.addBook();
        return "Книга добавлена";
    }

    @ShellMethod(value = "Edit a book", key = {"be", "book-edit"})
    public String editBook() {
        bookService.updateBook();
        return "Книга изменена";
    }

    @ShellMethod(value = "Delete a book", key = {"bd", "book-delete"})
    public String deleteBook() {
        bookService.deleteBook();
        return "Книга удалена";
    }

}
