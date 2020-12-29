package ru.otus.spring.homework.spring05.shell;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.shell.standard.ShellCommandGroup;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.otus.spring.homework.spring05.service.AuthorService;

@ShellComponent
@ShellCommandGroup("Author commands")
public class AuthorShellCommands {
    private static final Logger log = LoggerFactory.getLogger(AuthorShellCommands.class);

    private final AuthorService authorService;

    public AuthorShellCommands(AuthorService authorService) {
        this.authorService = authorService;
    }

    @ShellMethod(value = "Print all authors", key = {"ap", "author-print"})
    public String printAuthors() {
        authorService.printAuthors();
        return "";
    }

    @ShellMethod(value = "Adding a author", key = {"aa", "author-add"})
    public String addAuthor() {
        authorService.addAuthor();
        return "Автор добавлен в систему";
    }

    @ShellMethod(value = "Edit a author", key = {"ae", "author-edit"})
    public String editAuthor() {
        authorService.updateAuthor();
        return "Автор изменен";
    }

    @ShellMethod(value = "Delete a author", key = {"ad", "author-delete"})
    public String deleteAuthor() {
        authorService.deleteAuthor();
        return "Автор удален";
    }
}
