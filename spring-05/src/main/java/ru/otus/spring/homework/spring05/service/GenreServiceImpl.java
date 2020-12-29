package ru.otus.spring.homework.spring05.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.otus.spring.homework.spring05.dao.GenreDao;
import ru.otus.spring.homework.spring05.domain.Genre;
import ru.otus.spring.homework.spring05.shell.ShellReader;

import java.util.List;

@Service
public class GenreServiceImpl implements GenreService {
    private static final Logger log = LoggerFactory.getLogger(GenreServiceImpl.class);

    private final ShellReader shellReader;
    private final GenreDao genreDao;

    public GenreServiceImpl(ShellReader shellReader, GenreDao genreDao) {
        this.shellReader = shellReader;
        this.genreDao = genreDao;
    }

    @Override
    public Genre getGenre() {
        printGenres();
        System.out.println("Выберите ID жанра книги: ");
        long genreId = Long.parseLong(shellReader.readShell());
        return genreDao.getById(genreId);
    }

    @Override
    public void addGenre() {
        long genreId = genreDao.getNextId();
        System.out.println("Введите наименование жанра: ");
        String genreName = shellReader.readShell();
        genreDao.insert(new Genre(genreId, genreName));
    }

    @Override
    public void updateGenre() {
        printGenres();
        System.out.println("Введите ID автора для редактирования: ");
        long genreId = Long.parseLong(shellReader.readShell());
        Genre genre = genreDao.getById(genreId);
        if (genre != null) {
            System.out.printf("Выбран жанр: id: %s, name: %s%n", genre.getId(), genre.getName());
            System.out.println("Введите новый жанр: ");
            String genreName = shellReader.readShell();
            genreDao.update(new Genre(genreId, genreName));
        }
    }

    @Override
    public void deleteGenre() {
        printGenres();
        System.out.println("Введите ID жанра для удаления: ");
        long genreId = Long.parseLong(shellReader.readShell());
        Genre genre = genreDao.getById(genreId);
        if (genre != null) {
            genreDao.deleteById(genreId);
        } else {
            System.out.println("Жанр не найден");
        }
    }

    @Override
    public void printGenres() {
        System.out.println("Существующие записи жанров в системе:");
        List<Genre> genreList = genreDao.getAll();
        genreList.forEach(genre -> System.out.printf("id: %s, name: %s%n", genre.getId(), genre.getName()));
        System.out.printf("Всего записей: %s%n", genreDao.count());
    }
}
