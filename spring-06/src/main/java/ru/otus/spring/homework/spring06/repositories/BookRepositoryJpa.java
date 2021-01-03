package ru.otus.spring.homework.spring06.repositories;

import org.hibernate.annotations.QueryHints;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.homework.spring06.models.Book;

import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
import java.util.Optional;

@Repository
public class BookRepositoryJpa implements BookRepository {

    public static final String JAVAX_PERSISTENCE_FETCHGRAPH = "javax.persistence.fetchgraph";
    public static final String AUTHOR_ENTITY_GRAPH = "author-entity-graph";

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public Book save(Book book) {
        if (book.getId() <= 0) {
            book.setId(getNextId());
            em.persist(book);
            return book;
        } else {
            return em.merge(book);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Book> findById(long id) {
        EntityGraph<?> authorGraph = em.getEntityGraph(AUTHOR_ENTITY_GRAPH);
        em.createQuery(
                "select b from Book b left join fetch b.genres where b.id = :id",
                Book.class)
                .setParameter("id", id)
                .setHint(QueryHints.PASS_DISTINCT_THROUGH, false)
                .setHint(JAVAX_PERSISTENCE_FETCHGRAPH, authorGraph)
                .getResultList();

        return em.createQuery(
                "select b from Book b left join fetch b.comments where b.id = :id",
                Book.class)
                .setParameter("id", id)
                .setHint(QueryHints.PASS_DISTINCT_THROUGH, false)
                .getResultList().stream().findFirst();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Book> findAll() {
        EntityGraph<?> authorGraph = em.getEntityGraph(AUTHOR_ENTITY_GRAPH);
        em.createQuery(
                "select distinct b from Book b left join fetch b.genres",
                Book.class)
                .setHint(QueryHints.PASS_DISTINCT_THROUGH, false)
                .setHint(JAVAX_PERSISTENCE_FETCHGRAPH, authorGraph)
                .getResultList();

        return em.createQuery(
                "select distinct b from Book b left join fetch b.comments",
                Book.class)
                .setHint(QueryHints.PASS_DISTINCT_THROUGH, false)
                .getResultList();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Book> findByName(String name) {
        EntityGraph<?> authorGraph = em.getEntityGraph(AUTHOR_ENTITY_GRAPH);
        em.createQuery(
                "select b from Book b left join fetch b.genres where b.name like :name",
                Book.class)
                .setParameter("name", "%" + name + "%")
                .setHint(QueryHints.PASS_DISTINCT_THROUGH, false)
                .setHint(JAVAX_PERSISTENCE_FETCHGRAPH, authorGraph)
                .getResultList();

        return em.createQuery(
                "select b from Book b left join fetch b.comments where b.name like :name",
                Book.class)
                .setParameter("name", "%" + name + "%")
                .setHint(QueryHints.PASS_DISTINCT_THROUGH, false)
                .getResultList().stream().findFirst();
    }

    @Override
    @Transactional
    public void updateNameById(long id, String name) {
        Query query = em.createQuery(
                "update Book b set b.name = :name where b.id = :id"
        );
        query.setParameter("name", name);
        query.setParameter("id", id);
        query.executeUpdate();
    }

    @Override
    @Transactional
    public void deleteById(long id) {
        Query query = em.createQuery(
                "delete from Book b where b.id = :id"
        );
        query.setParameter("id", id);
        query.executeUpdate();
    }

    @Override
    @Transactional(readOnly = true)
    public long getNextId() {
        Query query = em.createNativeQuery(
                "select next value for BOOK_SEQUENCE"
                , Long.class);
        return query.getFirstResult();
    }

    @Override
    @Transactional(readOnly = true)
    public long count() {
        Query query = em.createQuery(
                "select count(b) from Book b"
                , Long.class);
        return (long) query.getSingleResult();
    }
}
