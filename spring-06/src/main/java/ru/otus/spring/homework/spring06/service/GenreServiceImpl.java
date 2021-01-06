package ru.otus.spring.homework.spring06.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.otus.spring.homework.spring06.models.Genre;
import ru.otus.spring.homework.spring06.repositories.GenreRepository;
import ru.otus.spring.homework.spring06.shell.ShellReader;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GenreServiceImpl implements GenreService {
    private static final Logger log = LoggerFactory.getLogger(GenreServiceImpl.class);

    private final ShellReader shellReader;
    private final GenreRepository genreRepository;

    public GenreServiceImpl(ShellReader shellReader, GenreRepository genreRepository) {
        this.shellReader = shellReader;
        this.genreRepository = genreRepository;
    }

    @Override
    public Genre getGenre() {
        printGenres();
        System.out.println("Выберите ID жанра книги: ");
        long genreId = Long.parseLong(shellReader.readShell());
        return genreRepository.findById(genreId).orElse(null);
    }

    @Override
    public List<Genre> getGenres() {
        printGenres();
        System.out.println("Введите через запятую один или несколько ID жанра книги: ");
        String genresIdString = shellReader.readShell();
        var items = Arrays.stream(genresIdString.split(",")).collect(Collectors.toList());
        List<Genre> genres = new ArrayList<>();
        for (String item : items) {
            genreRepository.findById(Long.parseLong(item)).ifPresent(genres::add);
        }
        return genres;
    }

    @Override
    public void addGenre() {
        System.out.println("Введите наименование нового жанра: ");
        String genreName = shellReader.readShell();
        genreRepository.save(new Genre(0, genreName));
    }

    @Override
    public void updateGenre() {
        printGenres();
        System.out.println("Введите ID автора для редактирования: ");
        long genreId = Long.parseLong(shellReader.readShell());
        Genre genre = genreRepository.findById(genreId).orElse(null);
        if (genre != null) {
            System.out.printf("Выбран жанр: id: %s, name: %s%n", genre.getId(), genre.getName());
            System.out.println("Введите новое наименование жанра: ");
            String genreName = shellReader.readShell();
            genre.setName(genreName);
            genreRepository.save(genre);
        } else {
            System.out.println("Жанр не найден");
        }
    }

    @Override
    public void deleteGenre() {
        printGenres();
        System.out.println("Введите ID жанра для удаления: ");
        long genreId = Long.parseLong(shellReader.readShell());
        Genre genre = genreRepository.findById(genreId).orElse(null);
        if (genre != null) {
            genreRepository.deleteById(genreId);
        } else {
            System.out.println("Жанр не найден");
        }
    }

    @Override
    public void printGenres() {
        System.out.println("Существующие записи жанров в системе:");
        List<Genre> genreList = genreRepository.findAll();
        genreList.forEach(genre -> System.out.printf("%s: name: %s%n", genre.getId(), genre.getName()));
        System.out.printf("Всего записей: %s%n", genreRepository.count());
    }
}
