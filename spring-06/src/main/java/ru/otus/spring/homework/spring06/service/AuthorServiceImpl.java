package ru.otus.spring.homework.spring06.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.otus.spring.homework.spring06.models.Author;
import ru.otus.spring.homework.spring06.repositories.AuthorRepository;
import ru.otus.spring.homework.spring06.shell.ShellReader;

import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService {
    private static final Logger log = LoggerFactory.getLogger(AuthorServiceImpl.class);

    private final ShellReader shellReader;
    private final AuthorRepository authorRepository;

    public AuthorServiceImpl(ShellReader shellReader, AuthorRepository authorRepository) {
        this.shellReader = shellReader;
        this.authorRepository = authorRepository;
    }

    @Override
    public Author getAuthor() {
        printAuthors();
        System.out.println("Выберите ID автора книги: ");
        long authorId = Long.parseLong(shellReader.readShell());
        return authorRepository.findById(authorId).orElse(null);
    }

    @Override
    public void addAuthor() {
        System.out.println("Введите имя автора: ");
        String authorName = shellReader.readShell();
        authorRepository.save(new Author(0, authorName));
    }

    @Override
    public void updateAuthor() {
        printAuthors();
        System.out.println("Введите ID автора для редактирования: ");
        long authorId = Long.parseLong(shellReader.readShell());
        Author author = authorRepository.findById(authorId).orElse(null);
        if (author != null) {
            System.out.printf("Выбран автор: id: %s, name: %s%n", author.getId(), author.getName());
            System.out.println("Введите новое имя автора: ");
            String authorName = shellReader.readShell();
            author.setName(authorName);
            authorRepository.save(author);
        } else {
            System.out.println("Автор не найден");
        }
    }

    @Override
    public void deleteAuthor() {
        printAuthors();
        System.out.println("Введите ID автора для удаления: ");
        long authorId = Long.parseLong(shellReader.readShell());
        Author author = authorRepository.findById(authorId).orElse(null);
        if (author != null) {
            authorRepository.deleteById(authorId);
        } else {
            System.out.println("Автор не найден");
        }
    }

    @Override
    public void printAuthors() {
        System.out.println("Существующие записи авторов в системе:");
        List<Author> authorList = authorRepository.findAll();
        authorList.forEach(author -> System.out.printf("%s: name: %s%n", author.getId(), author.getName()));
        System.out.printf("Всего записей: %s%n", authorRepository.count());
    }
}
