package ru.otus.spring.homework.spring05.shell;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.shell.standard.ShellCommandGroup;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.otus.spring.homework.spring05.service.GenreService;

@ShellComponent
@ShellCommandGroup("Genre commands")
public class GenreShellCommands {
    private static final Logger log = LoggerFactory.getLogger(GenreShellCommands.class);

    private final GenreService genreService;

    public GenreShellCommands(GenreService genreService) {
        this.genreService = genreService;
    }

    @ShellMethod(value = "Print all genres", key = {"gp", "genre-print"})
    public String printGenres() {
        genreService.printGenres();
        return "";
    }

    @ShellMethod(value = "Adding a genre", key = {"ga", "genre-add"})
    public String addGenre() {
        genreService.addGenre();
        return "Жанр добавлен в систему";
    }

    @ShellMethod(value = "Edit a genre", key = {"ge", "genre-edit"})
    public String editGenre() {
        genreService.updateGenre();
        return "Жанр изменен";
    }

    @ShellMethod(value = "Delete a genre", key = {"gd", "genre-delete"})
    public String deleteGenre() {
        genreService.deleteGenre();
        return "Жанр удален";
    }
}
