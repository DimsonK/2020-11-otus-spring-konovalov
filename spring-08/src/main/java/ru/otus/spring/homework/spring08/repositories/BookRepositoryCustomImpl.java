package ru.otus.spring.homework.spring08.repositories;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import ru.otus.spring.homework.spring08.models.Author;
import ru.otus.spring.homework.spring08.models.Book;
import ru.otus.spring.homework.spring08.models.Genre;

public class BookRepositoryCustomImpl implements BookRepositoryCustom {

    private final MongoTemplate mongoTemplate;

    public BookRepositoryCustomImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public boolean authorExistsInBooks(Author author) {
        Query searchQuery = new Query(Criteria.where("author").is(author));
        return mongoTemplate.exists(searchQuery, Book.class);
    }

    @Override
    public boolean genreExistsInBooks(Genre genre) {
        Query searchQuery = new Query(Criteria.where("genres.name").is(genre.getName()));
        return mongoTemplate.exists(searchQuery, Book.class);
    }

    @Override
    public void updateAuthorInBooks(Author author) {
        Query query = new Query(Criteria.where("author.id").is(author.getId()));
        Update update = new Update().set("author.name", author.getName());
        mongoTemplate.updateMulti(query, update, Book.class);
    }

    @Override
    public void updateGenreInBooks(Genre genre) {
        Query query = new Query(Criteria.where("genres.id").is(genre.getId()));
        Update update = new Update().set("genres.$", genre);
        mongoTemplate.updateMulti(query, update, Book.class);
    }
}
