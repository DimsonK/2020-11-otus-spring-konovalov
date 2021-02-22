package ru.otus.spring.homework.spring11.repositories;

import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.spring.homework.spring11.models.entity.Comment;
import ru.otus.spring.homework.spring11.utils.RawResultPrinter;


@RequiredArgsConstructor
public class CommentRepositoryCustomImpl implements CommentRepositoryCustom {

    private static final String BOOK_ID_CRITERIA = "book.$id";

    private final ReactiveMongoTemplate mongoTemplate;
    private final RawResultPrinter rawResultPrinter;

    @Override
    public Flux<Comment> findByBookId(String bookId) {
        Query searchQuery = new Query(Criteria.where(BOOK_ID_CRITERIA).is(new ObjectId(bookId)));
        return mongoTemplate.find(searchQuery, Comment.class);
    }

    @Override
    public Flux<Comment> findFavoritesByBookId(String bookId) {
        Query searchQuery = new Query(Criteria.where(BOOK_ID_CRITERIA).is(new ObjectId(bookId)).and("favorite").is(true));
        return mongoTemplate.find(searchQuery, Comment.class);
    }

    @Override
    public Mono<Void> deleteByBookId(String bookId) {
        Query searchQuery = new Query(Criteria.where(BOOK_ID_CRITERIA).is(new ObjectId(bookId)));
        return mongoTemplate.remove(searchQuery, Comment.class).then();
    }
}
