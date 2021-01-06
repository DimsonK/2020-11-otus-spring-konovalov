package ru.otus.spring.homework.spring06.repositories;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.homework.spring06.models.Book;
import ru.otus.spring.homework.spring06.models.Comment;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

@Repository
public class CommentRepositoryJpa implements CommentRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public Comment save(Comment comment) {
        if (comment.getId() == 0) {
            em.persist(comment);
            return comment;
        } else {
            return em.merge(comment);
        }
    }

    @Override
    public Optional<Comment> findById(long id) {
        return Optional.ofNullable(em.find(Comment.class, id));
    }

    @Override
    public List<Comment> findAll() {
        TypedQuery<Comment> query = em.createQuery("select c from Comment c", Comment.class);
        return query.getResultList();
    }

    @Override
    public List<Comment> findByBook(Book book) {
        TypedQuery<Comment> query = em.createQuery(
                "select c from Comment c where c.book = :book",
                Comment.class);
        query.setParameter("book", book);
        return query.getResultList();
    }

    @Override
    public List<Comment> findByContent(String content) {
        TypedQuery<Comment> query = em.createQuery(
                "select c from Comment c where c.content = :content",
                Comment.class);
        query.setParameter("content", content);
        return query.getResultList();
    }

    @Override
    public List<Comment> findByAuthor(String authorName) {
        TypedQuery<Comment> query = em.createQuery(
                "select c from Comment c where c.authorName = :authorName",
                Comment.class);
        query.setParameter("authorName", authorName);
        return query.getResultList();
    }

    @Override
    @Transactional
    public void deleteById(long id) {
        findById(id).ifPresent(comment -> em.remove(comment));
    }

    @Override
    public long count() {
        Query query = em.createQuery("select count(c) from Comment c", Long.class);
        return (long) query.getSingleResult();
    }
}
