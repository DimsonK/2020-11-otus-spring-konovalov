package ru.otus.spring.homework.spring05.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.otus.spring.homework.spring05.dao.AuthorDao;
import ru.otus.spring.homework.spring05.domain.Author;
import ru.otus.spring.homework.spring05.shell.ShellReader;

import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService {
    private static final Logger log = LoggerFactory.getLogger(AuthorServiceImpl.class);

    private final ShellReader shellReader;
    private final AuthorDao authorDao;

    public AuthorServiceImpl(ShellReader shellReader, AuthorDao authorDao) {
        this.shellReader = shellReader;
        this.authorDao = authorDao;
    }

    @Override
    public Author getAuthor() {
        printAuthors();
        System.out.println("Выберите ID автора книги: ");
        long authorId = Long.parseLong(shellReader.readShell());
        return authorDao.getById(authorId);
    }

    @Override
    public void addAuthor() {
        long authorId = authorDao.getNextId();
        System.out.println("Введите имя автора: ");
        String authorName = shellReader.readShell();
        authorDao.insert(new Author(authorId, authorName));
    }

    @Override
    public void updateAuthor() {
        printAuthors();
        System.out.println("Введите ID автора для редактирования: ");
        long authorId = Long.parseLong(shellReader.readShell());
        Author author = authorDao.getById(authorId);
        if (author != null) {
            System.out.printf("Выбран автор: id: %s, name: %s%n", author.getId(), author.getName());
            System.out.println("Введите новое имя автора: ");
            String authorName = shellReader.readShell();
            authorDao.update(new Author(authorId, authorName));
        } else {
            System.out.println("Автор не найден");
        }
    }

    @Override
    public void deleteAuthor() {
        printAuthors();
        System.out.println("Введите ID автора для удаления: ");
        long authorId = Long.parseLong(shellReader.readShell());
        Author author = authorDao.getById(authorId);
        if (author != null) {
            authorDao.deleteById(authorId);
        } else {
            System.out.println("Автор не найден");
        }
    }

    @Override
    public void printAuthors() {
        System.out.println("Существующие записи авторов в системе:");
        List<Author> authorList = authorDao.getAll();
        authorList.forEach(author -> System.out.printf("id: %s, name: %s%n", author.getId(), author.getName()));
        System.out.printf("Всего записей: %s%n", authorDao.count());
    }
}
