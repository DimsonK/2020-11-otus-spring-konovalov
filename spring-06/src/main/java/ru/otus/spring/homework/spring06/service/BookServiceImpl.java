package ru.otus.spring.homework.spring06.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.otus.spring.homework.spring06.models.Author;
import ru.otus.spring.homework.spring06.models.Book;
import ru.otus.spring.homework.spring06.models.Genre;
import ru.otus.spring.homework.spring06.repositories.BookRepository;
import ru.otus.spring.homework.spring06.shell.ShellReader;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {
    private static final Logger log = LoggerFactory.getLogger(BookServiceImpl.class);

    private final AuthorService authorService;
    private final GenreService genreService;
    private final BookRepository bookRepository;
    private final ShellReader shellReader;

    public BookServiceImpl(BookRepository bookRepository,
            AuthorService authorService,
            GenreService genreService,
            ShellReader shellReader) {
        this.bookRepository = bookRepository;
        this.authorService = authorService;
        this.genreService = genreService;
        this.shellReader = shellReader;
    }

    @Override
    public Book getBook() {
        printBooks();
        System.out.println("Выберите ID книги: ");
        long bookId = Long.parseLong(shellReader.readShell());
        return bookRepository.findById(bookId).orElse(null);
    }

    @Override
    public void addBook() {
        System.out.println("Введите название книги: ");
        String bookName = shellReader.readShell();
        Author author = authorService.getAuthor();
        List<Genre> genres = genreService.getGenres();
        bookRepository.save(new Book(-1, bookName, author, genres, null));
    }

    @Override
    public void updateBook() {
        printBooks();
        System.out.println("Введите ID книги");
        long bookId = Long.parseLong(shellReader.readShell());
        Book book = bookRepository.findById(bookId).orElse(null);
        if (book != null) {
            System.out.printf("Выбрана книга: id: %s, name: %s%n", book.getId(), book.getName());
            System.out.println("Введите новое название книги: ");
            String bookName = shellReader.readShell();
            Author author = authorService.getAuthor();
            List<Genre> genres = genreService.getGenres();
            book.setName(bookName);
            book.setAuthor(author);
            book.setGenres(genres);
            bookRepository.save(book);
        } else {
            System.out.println("Книга не найдена");
        }
    }

    @Override
    public void deleteBook() {
        printBooks();
        System.out.println("Введите ID книги для удаления: ");
        long bookId = Long.parseLong(shellReader.readShell());
        Book book = bookRepository.findById(bookId).orElse(null);
        if (book != null) {
            bookRepository.deleteById(bookId);
        } else {
            System.out.println("Книга не найдена");
        }
    }

    @Override
    public void printBooks() {
        System.out.println("Существующие записи книг в системе:");
        List<Book> bookList = bookRepository.findAll();
        bookList.forEach(book -> {
            System.out.printf(
                    "%s: name: %s | author: %s | ",
                    book.getId(), book.getName(), book.getAuthor().getName()
            );
            System.out.printf("genres: %s | ", book.getGenres().stream().map(genre -> String.format("%s (%s)", genre.getName(), genre.getId()))
                    .collect(Collectors.joining(", ")));
            System.out.printf("total comments: %s%n", book.getComments().size());
        });
        System.out.printf("Total books: %s%n", bookRepository.count());
    }

    @Override
    public void printBookComments() {
        printBooks();
        System.out.println("Выберите книгу для просмотре комментариев:");
        long bookId = Long.parseLong(shellReader.readShell());
        var book = bookRepository.findById(bookId).orElse(null);
        if (book != null) {
            book.getComments().forEach(comment ->
                    System.out.printf("id: %s, date: %s, author: %s,%n content: %s%n",
                            comment.getId(), comment.getPostDate(), comment.getAuthorName(), comment.getContent()));
        } else {
            System.out.printf("Book with id: %s not found", bookId);
        }
    }
}
