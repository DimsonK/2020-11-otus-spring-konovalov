package ru.otus.spring.homework.spring08.shell;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.shell.standard.ShellCommandGroup;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.otus.spring.homework.spring08.exceptions.BadRequestException;
import ru.otus.spring.homework.spring08.models.Genre;
import ru.otus.spring.homework.spring08.service.GenreService;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@ShellComponent
@ShellCommandGroup("Genre commands")
public class GenreShellCommands {
    private static final Logger log = LoggerFactory.getLogger(GenreShellCommands.class);

    private final GenreService genreService;
    private final ShellReader shellReader;

    public GenreShellCommands(GenreService genreService, ShellReader shellReader) {
        this.genreService = genreService;
        this.shellReader = shellReader;
    }

    @ShellMethod(value = "Print all genres", key = {"gp", "genre-print"})
    public void printGenres() {
        log.debug("printGenres()");
        System.out.println("Существующие записи жанров в системе:");
        List<Genre> genreList = genreService.getAll();
        showGenreList(genreList);
        System.out.printf("Всего записей: %s%n", genreService.getCount());
    }

    @ShellMethod(value = "Adding a genre", key = {"ga", "genre-add"})
    public String addGenre() {
        log.debug("addGenre()");
        System.out.println("Введите наименование нового жанра: ");
        String genreName = shellReader.readShell();
        var newGenre = genreService.addGenre(genreName);
        return String.format("Жанр '%s' добавлен в систему", newGenre.getName());
    }

    @ShellMethod(value = "Edit a genre", key = {"ge", "genre-edit"})
    public String editGenre() {
        log.debug("editGenre()");
        System.out.println("Введите ID жанра для редактирования: ");
        var genre = getGenre();
        if (genre != null) {
            System.out.printf("Выбран жанр: id: %s, name: %s%n", genre.getId(), genre.getName());
            System.out.println("Введите новое наименование жанра: ");
            String genreName = shellReader.readShell();
            genre.setName(genreName);
            genreService.updateGenre(genre);
        } else {
            System.out.println("Жанр не найден");
        }
        return "Жанр изменен";
    }

    @ShellMethod(value = "Delete a genre", key = {"gd", "genre-delete"})
    public String deleteGenre() {
        log.debug("deleteGenre()");
        System.out.println("Введите ID жанра для удаления: ");
        Genre genre = getGenre();
        if (genre != null) {
            if (!genreService.isExistsInBooks(genre)) {
                genreService.deleteGenre(genre.getId());
            } else {
                throw new BadRequestException("Жанр есть в книгах, удаление невозможно");
            }
        } else {
            System.out.println("Жанр не найден");
        }
        return "Жанр удален";
    }

    public Genre getGenre() {
        log.debug("getGenre()");
        List<Genre> genreList = genreService.getAll();
        showGenreList(genreList);
        System.out.println("Выберите ID жанра книги: ");
        var genreNumber = Integer.parseInt(shellReader.readShell());
        var genreId = genreList.get(genreNumber) != null ? genreList.get(genreNumber).getId() : "";
        return genreService.getGenre(genreId);
    }

    public List<Genre> getGenres() {
        log.debug("getGenres()");
        System.out.println("Введите через запятую один или несколько ID жанра книги: ");
        List<Genre> genreList = genreService.getAll();
        showGenreList(genreList);
        String genresIdString = shellReader.readShell();
        return Arrays.stream(genresIdString.split(","))
                .map(Integer::parseInt)
                .map(genreList::get)
                .collect(Collectors.toList());
    }

    private void showGenreList(List<Genre> genreList) {
        int[] idx = {0};
        genreList.forEach(genre -> System.out.printf("%s: name: %s%n", idx[0]++, genre.getName()));
    }
}
