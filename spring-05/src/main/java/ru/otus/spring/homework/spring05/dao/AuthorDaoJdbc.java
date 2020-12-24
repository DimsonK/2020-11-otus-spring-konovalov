package ru.otus.spring.homework.spring05.dao;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;
import ru.otus.spring.homework.spring05.domain.Author;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Repository
public class AuthorDaoJdbc implements AuthorDao {

    private final NamedParameterJdbcOperations namedParameterJdbcOperations;

    public AuthorDaoJdbc(NamedParameterJdbcOperations namedParameterJdbcOperations) {
        this.namedParameterJdbcOperations = namedParameterJdbcOperations;
    }

    public long getNextId() {
        var jdbc = namedParameterJdbcOperations.getJdbcOperations();
        return jdbc.queryForObject("select next value for AUTHOR_SEQUENCE", Long.class);
    }

    @Override
    public int count() {
        var jdbc = namedParameterJdbcOperations.getJdbcOperations();
        return jdbc.queryForObject("select count(*) from AUTHORS", Integer.class);
    }

    @Override
    public void insert(Author author) {
        Map<String, Object> params = Map.of("id", author.getId(), "name", author.getName());
        namedParameterJdbcOperations.update("insert into AUTHORS (ID, AUTHOR_NAME) values (:id, :name)", params);
    }

    @Override
    public void update(Author author) {
        Map<String, Object> params = Map.of("id", author.getId(), "name", author.getName());
        namedParameterJdbcOperations.update("update AUTHORS set ID = :id, AUTHOR_NAME = :name where ID = :id", params);
    }

    @Override
    public Author getById(long id) {
        Map<String, Object> params = Collections.singletonMap("id", id);
        return namedParameterJdbcOperations.queryForObject(
                "select ID, AUTHOR_NAME from AUTHORS where ID = :id", params, new AuthorMapper()
        );
    }

    @Override
    public List<Author> getAll() {
        return namedParameterJdbcOperations.query("select ID, AUTHOR_NAME from AUTHORS", new AuthorMapper());
    }

    @Override
    public void deleteById(long id) {
        Map<String, Object> params = Collections.singletonMap("id", id);
        namedParameterJdbcOperations.update(
                "delete from AUTHORS where ID = :id", params
        );
    }

    private static class AuthorMapper implements RowMapper<Author> {

        @Override
        public Author mapRow(ResultSet resultSet, int i) throws SQLException {
            long id = resultSet.getLong("ID");
            String name = resultSet.getString("AUTHOR_NAME");
            return new Author(id, name);
        }
    }
}
