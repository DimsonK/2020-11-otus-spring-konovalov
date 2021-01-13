package ru.otus.spring.homework.spring06.repositories;

import org.springframework.stereotype.Repository;
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
    public void deleteById(long id) {
        findById(id).ifPresent(comment -> em.remove(comment));
    }

    @Override
    public long count() {
        Query query = em.createQuery("select count(c) from Comment c", Long.class);
        return (long) query.getSingleResult();
    }
}
