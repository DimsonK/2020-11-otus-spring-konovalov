package ru.otus.spring.homework.spring05.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.otus.spring.homework.spring05.dao.BookDao;
import ru.otus.spring.homework.spring05.domain.Author;
import ru.otus.spring.homework.spring05.domain.Book;
import ru.otus.spring.homework.spring05.domain.Genre;
import ru.otus.spring.homework.spring05.shell.ShellReader;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {
    private static final Logger log = LoggerFactory.getLogger(BookServiceImpl.class);

    private final AuthorService authorService;
    private final GenreService genreService;
    private final BookDao bookDao;
    private final ShellReader shellReader;

    public BookServiceImpl(BookDao bookDao,
            AuthorService authorService,
            GenreService genreService,
            ShellReader shellReader) {
        this.bookDao = bookDao;
        this.authorService = authorService;
        this.genreService = genreService;
        this.shellReader = shellReader;
    }

    @Override
    public Book getBook() {
        printBooks();
        System.out.println("Выберите ID книги: ");
        long bookId = Long.parseLong(shellReader.readShell());
        return bookDao.getById(bookId);
    }

    @Override
    public void addBook() {
        long bookId = bookDao.getNextId();
        System.out.println("Введите название книги: ");
        String bookName = shellReader.readShell();
        Author author = authorService.getAuthor();
        Genre genre = genreService.getGenre();
        bookDao.insert(new Book(bookId, bookName, author, genre));
    }

    @Override
    public void updateBook() {
        System.out.println("Введите ID книги");
        long bookId = Long.parseLong(shellReader.readShell());
        Book book = bookDao.getById(bookId);
        if (book != null) {
            System.out.printf("Выбрана книга: id: %s, name: %s%n", book.getId(), book.getName());
            System.out.println("Введите новое название книги: ");
            String bookName = shellReader.readShell();
            Author author = authorService.getAuthor();
            Genre genre = genreService.getGenre();
            bookDao.update(new Book(bookId, bookName, author, genre));
        }
    }

    @Override
    public void deleteBook() {
        printBooks();
        System.out.println("Введите ID книги для удаления: ");
        long bookId = Long.parseLong(shellReader.readShell());
        Book book = bookDao.getById(bookId);
        if (book != null) {
            bookDao.deleteById(bookId);
        } else {
            System.out.println("Книга не найдена");
        }
    }

    @Override
    public void printBooks() {
        System.out.println("Существующие записи книг в системе:");
        List<Book> bookList = bookDao.getAll();
        bookList.forEach(book -> System.out.printf(
                "id: %s, name: %s, author: %s, genre: %s%n",
                book.getId(), book.getName(), book.getAuthor().getName(), book.getGenre().getName()));
        System.out.printf("Всего записей: %s%n", bookDao.count());
    }
}
