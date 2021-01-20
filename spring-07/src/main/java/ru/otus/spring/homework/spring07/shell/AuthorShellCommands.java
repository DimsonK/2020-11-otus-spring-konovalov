package ru.otus.spring.homework.spring07.shell;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.shell.standard.ShellCommandGroup;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.otus.spring.homework.spring07.models.Author;
import ru.otus.spring.homework.spring07.service.AuthorService;

import java.util.List;

@ShellComponent
@ShellCommandGroup("Author commands")
public class AuthorShellCommands {
    private static final Logger log = LoggerFactory.getLogger(AuthorShellCommands.class);

    private final AuthorService authorService;
    private final ShellReader shellReader;

    public AuthorShellCommands(AuthorService authorService, ShellReader shellReader) {
        this.authorService = authorService;
        this.shellReader = shellReader;
    }

    @ShellMethod(value = "Print all authors", key = {"ap", "author-print"})
    public void printAuthors() {
        log.debug("printAuthors()");
        System.out.println("Существующие записи авторов в системе:");
        List<Author> authorList = authorService.getAll();
        authorList.forEach(author -> System.out.printf("%s: name: %s%n", author.getId(), author.getName()));
        System.out.printf("Всего записей: %s%n", authorService.getCount());
    }

    @ShellMethod(value = "Adding a author", key = {"aa", "author-add"})
    public String addAuthor() {
        log.debug("addAuthor()");
        System.out.println("Введите имя автора: ");
        String authorName = shellReader.readShell();
        var author = authorService.addAuthor(authorName);
        return String.format("Автор с именем: %s добавлен в систему", author.getName());
    }

    @ShellMethod(value = "Edit a author", key = {"ae", "author-edit"})
    public String editAuthor() {
        log.debug("editAuthor()");
        printAuthors();
        System.out.println("Введите ID автора для редактирования: ");
        long authorId = Long.parseLong(shellReader.readShell());
        var author = authorService.getAuthor(authorId);
        if (author != null) {
            System.out.printf("Выбран автор: id: %s, name: %s%n", author.getId(), author.getName());
            System.out.println("Введите новое имя автора: ");
            String authorName = shellReader.readShell();
            author.setName(authorName);
        } else {
            System.out.println("Автор не найден");
        }
        var newAuthor = authorService.updateAuthor(author);
        return String.format("Автор с id: %s изменен", newAuthor.getId());
    }

    @ShellMethod(value = "Delete a author", key = {"ad", "author-delete"})
    public String deleteAuthor() {
        log.debug("deleteAuthor()");
        printAuthors();
        System.out.println("Введите ID автора для удаления: ");
        long authorId = Long.parseLong(shellReader.readShell());
        var author = authorService.getAuthor(authorId);
        if (author != null) {
            authorService.deleteAuthor(authorId);
        } else {
            System.out.println("Автор не найден");
        }
        return "Автор удален";
    }

    public Author getAuthor() {
        log.debug("getAuthor()");
        printAuthors();
        System.out.println("Выберите ID автора книги: ");
        long authorId = Long.parseLong(shellReader.readShell());
        return authorService.getAuthor(authorId);
    }
}
