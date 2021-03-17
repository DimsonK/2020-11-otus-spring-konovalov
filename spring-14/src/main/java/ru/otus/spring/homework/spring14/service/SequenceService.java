package ru.otus.spring.homework.spring14.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Service;
import ru.otus.spring.homework.spring14.model.h2.*;
import ru.otus.spring.homework.spring14.model.mongo.Author;
import ru.otus.spring.homework.spring14.model.mongo.Book;
import ru.otus.spring.homework.spring14.model.mongo.Comment;
import ru.otus.spring.homework.spring14.model.mongo.Genre;

import java.util.*;

@Slf4j
@Service
public class SequenceService {

    private final Map<String, Long> authorIdMap = new HashMap<>();
    private final Map<String, Long> genreIdMap = new HashMap<>();
    private final Map<String, Long> bookIdMap = new HashMap<>();
    private final List<BookGenresDao> bookGenresDaoList = new ArrayList<>();

    private final NamedParameterJdbcOperations namedParameterJdbcOperations;

    public SequenceService(NamedParameterJdbcOperations namedParameterJdbcOperations) {
        this.namedParameterJdbcOperations = namedParameterJdbcOperations;
    }

    public GenreDao updateGenreId(Genre genre) {
        log.info("input genre: " + genre.toString());
        var id = getSequence("GENRE_SEQUENCE");
        genreIdMap.put(genre.getId(), id);
        return new GenreDao(id, genre.getName());
    }

    public AuthorDao updateAuthorId(Author author) {
        log.info("input author: " + author.getName());
        var id = getSequence("AUTHOR_SEQUENCE");
        authorIdMap.put(author.getId(), id);
        return new AuthorDao(id, author.getName());
    }

    public BookDao updateBookId(Book book) {
        log.info("input book: " + book.getName());
        var id = getSequence("BOOK_SEQUENCE");
        var authorId = Objects.nonNull(authorIdMap) ? authorIdMap.get(book.getAuthor().getId()) : null;
        book.getGenres().forEach(genre -> {
            var genreId = Objects.nonNull(genreIdMap) ? genreIdMap.get(genre.getId()) : null;
            bookGenresDaoList.add(new BookGenresDao(id, genreId));
        });
        bookIdMap.put(book.getId(), id);
        return new BookDao(id, book.getName(), authorId);
    }

    public CommentDao updateCommentId(Comment comment) {
        log.info("input comment: " + comment.getAuthorName());
        var id = getSequence("COMMENT_SEQUENCE");
        var bookId = Objects.nonNull(bookIdMap) ? bookIdMap.get(comment.getBook().getId()) : null;
        return new CommentDao(id, comment.getPostDate(), comment.getAuthorName(), comment.getContent(), comment.isFavorite(), bookId);
    }

    public List<BookGenresDao> getBookGenresDaoList() {
        return bookGenresDaoList;
    }

    private Long getSequence(String sequenceName) {
        var jdbc = namedParameterJdbcOperations.getJdbcOperations();
        return jdbc.queryForObject(String.format("select next value for %s", sequenceName), Long.class);
    }

}
