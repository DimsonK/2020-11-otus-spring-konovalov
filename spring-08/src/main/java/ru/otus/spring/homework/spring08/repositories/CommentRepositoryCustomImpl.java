package ru.otus.spring.homework.spring08.repositories;

import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import ru.otus.spring.homework.spring08.models.Comment;
import ru.otus.spring.homework.spring08.utils.RawResultPrinter;

import java.util.List;


@RequiredArgsConstructor
public class CommentRepositoryCustomImpl implements CommentRepositoryCustom {

    private final MongoTemplate mongoTemplate;
    private final RawResultPrinter rawResultPrinter;

    @Override
    public List<Comment> findByBookId(String bookId) {
        Query searchQuery = new Query(Criteria.where("book.$id").is(new ObjectId(bookId)));
        return mongoTemplate.find(searchQuery, Comment.class);
    }

    @Override
    public List<Comment> findFavoritesByBookId(String bookId) {
        Query searchQuery = new Query(Criteria.where("book.$id").is(new ObjectId(bookId)).and("favorite").is(true));
        return mongoTemplate.find(searchQuery, Comment.class);
    }

    @Override
    public void deleteByBookId(String bookId) {
        Query searchQuery = new Query(Criteria.where("book.$id").is(new ObjectId(bookId)));
        mongoTemplate.remove(searchQuery, Comment.class);
    }
}
