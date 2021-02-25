package ru.otus.spring.homework.spring11.repositories;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import reactor.core.publisher.Mono;
import ru.otus.spring.homework.spring11.models.entity.Author;
import ru.otus.spring.homework.spring11.models.entity.Book;
import ru.otus.spring.homework.spring11.models.entity.Genre;

public class BookRepositoryCustomImpl implements BookRepositoryCustom {

    private final ReactiveMongoTemplate mongoTemplate;

    public BookRepositoryCustomImpl(ReactiveMongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public Mono<Boolean> authorExistsInBooks(Author author) {
        if (author != null) {
            Query searchQuery = new Query(Criteria.where("author").is(author));
            return mongoTemplate.exists(searchQuery, Book.class);
        }
        return null;
    }

    @Override
    public Mono<Boolean> genreExistsInBooks(Genre genre) {
        if (genre != null) {
            Query searchQuery = new Query(Criteria.where("genres.name").is(genre.getName()));
            return mongoTemplate.exists(searchQuery, Book.class);
        }
        return null;
    }

    @Override
    public void updateAuthorInBooks(Author author) {
        Query query = new Query(Criteria.where("author._id").is(new ObjectId(author.getId())));
        Update update = new Update().set("author", author);
        mongoTemplate.updateMulti(query, update, Book.class).subscribe();
    }

    @Override
    public void updateGenreInBooks(Genre genre) {
        Query query = new Query(Criteria.where("genres._id").is(new ObjectId(genre.getId())));
        Update update = new Update().set("genres.$", genre);
        mongoTemplate.updateMulti(query, update, Book.class).subscribe();
    }
}
