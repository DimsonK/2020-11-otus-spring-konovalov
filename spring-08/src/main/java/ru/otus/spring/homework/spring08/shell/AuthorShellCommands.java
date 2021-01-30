package ru.otus.spring.homework.spring08.shell;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.shell.standard.ShellCommandGroup;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.otus.spring.homework.spring08.exceptions.BadRequestException;
import ru.otus.spring.homework.spring08.models.Author;
import ru.otus.spring.homework.spring08.service.AuthorService;

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
        showAuthorList(authorList);
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
        System.out.println("Введите номер автора для редактирования: ");
        var author = getAuthor();
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
        System.out.println("Введите номер автора для удаления: ");
        var author = getAuthor();
        if (author != null) {
            if (!authorService.isExistsInBook(author)) {
                authorService.deleteAuthor(author.getId());
            } else {
                throw new BadRequestException("Автор есть в книгах, удаление невозможно");
            }
        } else {
            System.out.println("Автор не найден");
        }
        return "Автор удален";
    }

    public Author getAuthor() {
        log.debug("getAuthor()");
        List<Author> authorList = authorService.getAll();
        showAuthorList(authorList);
        System.out.println("Выберите номер автора книги: ");
        var authorNumber = Integer.parseInt(shellReader.readShell());
        var authorId = authorList.get(authorNumber) != null ? authorList.get(authorNumber).getId() : "";
        return authorService.getAuthor(authorId);
    }

    private void showAuthorList(List<Author> authorList) {
        int[] idx = {0};
        authorList.forEach(author -> System.out.printf("%s: name: %s%n", idx[0]++, author.getName()));
    }
}
