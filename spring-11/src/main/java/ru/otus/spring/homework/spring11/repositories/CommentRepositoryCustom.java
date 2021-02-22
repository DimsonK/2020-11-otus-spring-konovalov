package ru.otus.spring.homework.spring11.repositories;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.spring.homework.spring11.models.entity.Comment;

public interface CommentRepositoryCustom {
    Flux<Comment> findByBookId(String bookId);

    Flux<Comment> findFavoritesByBookId(String bookId);

    Mono<Void> deleteByBookId(String bookId);
}
