package ru.otus.spring.homework.spring06.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.homework.spring06.models.Book;
import ru.otus.spring.homework.spring06.repositories.BookRepository;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {
    private static final Logger log = LoggerFactory.getLogger(BookServiceImpl.class);

    private final BookRepository bookRepository;

    public BookServiceImpl(
            BookRepository bookRepository
    ) {
        this.bookRepository = bookRepository;
    }

    @Override
    public List<Book> getAll() {
        log.debug("getAll()");
        return bookRepository.findAll();
    }

    @Override
    public Book getBook(long bookId) {
        log.debug("getBook()");
        return bookRepository.findById(bookId).orElse(null);
    }

    /**
     * Получение полного объекта книги
     * @param bookId id книги
     * @return book
     */
    @Override
    @Transactional(readOnly = true) // связанные сущности должны вычитываться в одной транзакции
    public Book getBookFull(long bookId) {
        // Variant 1
        // Попытка получить одним запросом все атрибуты, приводят к исключению: MultipleBagFetchException
        // Получаем отдельно отдельными графами
        // В данном случае Hibernate формирует два запроса
        bookRepository.findWithGraph(bookId, Book.WITH_AUTHOR_AND_GENRES_GRAPH);
        return bookRepository.findWithGraph(bookId, Book.WITH_COMMENTS_GRAPH).orElse(null);

        // Variant 2
        // Можно и так, но запросов в этом случае формируется больше
//        var book = bookRepository.findById(bookId).orElse(null);
//        if (book != null) {
//            book.toString(); // дёргает все публичные методы, что заставляет jpa выгрузить весь граф
//        }
//        return book;

    }

    @Override
    @Transactional
    public Book addBook(Book book) {
        log.debug("addBook()");
        return bookRepository.save(book);
    }

    @Override
    @Transactional
    public Book updateBook(Book book) {
        log.debug("updateBook()");
        return bookRepository.save(book);
    }

    @Override
    @Transactional
    public void deleteBook(long bookId) {
        log.debug("deleteBook()");
        bookRepository.deleteById(bookId);
    }

    @Override
    public long getCount() {
        log.debug("getCount()");
        return bookRepository.count();
    }

}
