package ru.otus.spring.homework.spring06.repositories;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
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
        if (comment.getId() <= 0) {
            comment.setId(getNextId());
            em.persist(comment);
            return comment;
        } else {
            return em.merge(comment);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Comment> findById(long id) {
        TypedQuery<Comment> query = em.createQuery(
                "select c from Comment c inner join fetch c.book where c.id = :id"
                , Comment.class);
        query.setParameter("id", id);
        return query.getResultList().stream().findFirst();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Comment> findAll() {
        TypedQuery<Comment> query = em.createQuery("select c from Comment c", Comment.class);
        return query.getResultList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Comment> findByContent(String content) {
        TypedQuery<Comment> query = em.createQuery(
                "select c from Comment c where c.content = :content",
                Comment.class);
        query.setParameter("content", content);
        return query.getResultList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Comment> findByAuthor(String authorName) {
        TypedQuery<Comment> query = em.createQuery(
                "select c from Comment c where c.authorName = :authorName",
                Comment.class);
        query.setParameter("authorName", authorName);
        return query.getResultList();
    }

    @Override
    @Transactional
    public void updateNameById(long id, String content) {
        Query query = em.createQuery(
                "update Comment c set c.content = :content where c.id = :id");
        query.setParameter("content", content);
        query.setParameter("id", id);
        query.executeUpdate();
    }

    @Override
    @Transactional
    public void deleteById(long id) {
        Query query = em.createQuery(
                "delete from Comment c where c.id = :id"
        );
        query.setParameter("id", id);
        query.executeUpdate();
    }

    @Override
    @Transactional(readOnly = true)
    public long getNextId() {
        Query query = em.createNativeQuery("select next value for COMMENT_SEQUENCE", Long.class);
        return query.getFirstResult();
    }

    @Override
    @Transactional(readOnly = true)
    public long count() {
        Query query = em.createQuery("select count(c) from Comment c", Long.class);
        return (long) query.getSingleResult();
    }
}
