package ru.otus.spring.homework.spring05.dao;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;
import ru.otus.spring.homework.spring05.domain.Author;
import ru.otus.spring.homework.spring05.domain.Book;
import ru.otus.spring.homework.spring05.domain.Genre;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Repository
public class BookDaoJdbc implements BookDao {

    private final NamedParameterJdbcOperations namedParameterJdbcOperations;
    private final AuthorDao authorDao;
    private final GenreDao genreDao;

    public BookDaoJdbc(NamedParameterJdbcOperations namedParameterJdbcOperations,
            AuthorDao authorDao, GenreDao genreDao) {
        this.namedParameterJdbcOperations = namedParameterJdbcOperations;
        this.authorDao = authorDao;
        this.genreDao = genreDao;
    }

    public long getNextId() {
        var jdbc = namedParameterJdbcOperations.getJdbcOperations();
        return jdbc.queryForObject("select next value for BOOK_SEQUENCE", Long.class);
    }

    @Override
    public int count() {
        var jdbc = namedParameterJdbcOperations.getJdbcOperations();
        return jdbc.queryForObject("select count(*) from BOOKS", Integer.class);
    }

    @Override
    public void insert(Book book) {
        Map<String, Object> params = Map.of(
                "id", book.getId(),
                "name", book.getName(),
                "author_id", book.getAuthor().getId(),
                "genre_id", book.getGenre().getId());
        namedParameterJdbcOperations.update(
                "insert into BOOKS (ID, BOOK_NAME, AUTHOR_ID, GENRE_ID) values (:id, :name, :author_id, :genre_id)", params);
    }

    @Override
    public void update(Book book) {
        Map<String, Object> params = Map.of(
                "id", book.getId(),
                "name", book.getName(),
                "author_id", book.getAuthor().getId(),
                "genre_id", book.getGenre().getId());
        namedParameterJdbcOperations.update(
                "update BOOKS set ID = :id, BOOK_NAME = :name, AUTHOR_ID = :author_id, GENRE_ID = :genre_id where ID = :id", params);
    }

    @Override
    public Book getById(long id) {
        Map<String, Object> params = Collections.singletonMap("id", id);
        return namedParameterJdbcOperations.queryForObject(
                "select ID, BOOK_NAME, AUTHOR_ID, GENRE_ID from BOOKS where ID = :id", params, new BookMapper(authorDao, genreDao)
        );
    }

    @Override
    public List<Book> getAll() {
        return namedParameterJdbcOperations.query("select ID, BOOK_NAME, AUTHOR_ID, GENRE_ID from BOOKS", new BookMapper(authorDao, genreDao));
    }

    @Override
    public void deleteById(long id) {
        Map<String, Object> params = Collections.singletonMap("id", id);
        namedParameterJdbcOperations.update(
                "delete from BOOKS where ID = :id", params
        );
    }

    private static class BookMapper implements RowMapper<Book> {
        private final AuthorDao authorDao;
        private final GenreDao genreDao;

        public BookMapper(AuthorDao authorDao, GenreDao genreDao) {
            this.authorDao = authorDao;
            this.genreDao = genreDao;
        }

        @Override
        public Book mapRow(ResultSet resultSet, int i) throws SQLException {
            long id = resultSet.getLong("ID");
            String name = resultSet.getString("BOOK_NAME");
            Author author = authorDao.getById(resultSet.getLong("AUTHOR_ID"));
            Genre genre = genreDao.getById(resultSet.getLong("GENRE_ID"));
            return new Book(id, name, author, genre);
        }
    }
}
