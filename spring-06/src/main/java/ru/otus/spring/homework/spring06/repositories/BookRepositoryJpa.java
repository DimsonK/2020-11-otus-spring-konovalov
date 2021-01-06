package ru.otus.spring.homework.spring06.repositories;

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
        if (book.getId() == 0) {
            em.persist(book);
            return book;
        } else {
            return em.merge(book);
        }
    }

    @Override
    public Optional<Book> findById(long id) {
        EntityGraph<?> authorGraph = em.getEntityGraph(AUTHOR_ENTITY_GRAPH);
        return em.createQuery(
                "select b from Book b left join fetch b.genres where b.id = :id",
                Book.class)
                .setParameter("id", id)
                .setHint(JAVAX_PERSISTENCE_FETCHGRAPH, authorGraph)
                .getResultList().stream().findFirst();
    }

    @Override
    public List<Book> findAll() {
        EntityGraph<?> authorGraph = em.getEntityGraph(AUTHOR_ENTITY_GRAPH);
        return em.createQuery(
                "select distinct b from Book b left join fetch b.genres",
                Book.class)
                .setHint(JAVAX_PERSISTENCE_FETCHGRAPH, authorGraph)
                .getResultList();
    }

    @Override
    public Optional<Book> findByName(String name) {
        EntityGraph<?> authorGraph = em.getEntityGraph(AUTHOR_ENTITY_GRAPH);
        return em.createQuery(
                "select b from Book b left join fetch b.genres where b.name like :name",
                Book.class)
                .setParameter("name", "%" + name + "%")
                .setHint(JAVAX_PERSISTENCE_FETCHGRAPH, authorGraph)
                .getResultList().stream().findFirst();
    }

    @Override
    @Transactional
    public void deleteById(long id) {
        findById(id).ifPresent(book -> em.remove(book));
    }

    @Override
    public long count() {
        Query query = em.createQuery(
                "select count(b) from Book b"
                , Long.class);
        return (long) query.getSingleResult();
    }
}
