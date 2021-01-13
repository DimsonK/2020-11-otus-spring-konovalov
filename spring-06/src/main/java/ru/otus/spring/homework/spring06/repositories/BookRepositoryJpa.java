package ru.otus.spring.homework.spring06.repositories;

import org.springframework.stereotype.Repository;
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

    @PersistenceContext
    private EntityManager em;

    @Override
    public Book save(Book book) {
        if (book.getId() == 0) {
            em.persist(book);
            return book;
        } else {
            return em.merge(book);
        }
    }

    @Override
    public Optional<Book> findById(long bookId) {
        return Optional.ofNullable(em.find(Book.class, bookId));
    }

    @Override
    public Optional<Book> findWithGraph(long bookId, String graphName) {
        EntityGraph<?> entityGraph = em.getEntityGraph(graphName);
        var book = em.createQuery(
                "select distinct b from Book b where b.id = :id",
                Book.class)
                .setParameter("id", bookId)
                .setHint(JAVAX_PERSISTENCE_FETCHGRAPH, entityGraph)
                .getSingleResult();
        return Optional.ofNullable(book);
    }

    @Override
    public List<Book> findAll() {
        EntityGraph<?> graph = em.getEntityGraph(Book.WITH_AUTHOR_AND_GENRES_GRAPH);
        return em.createQuery(
                "select distinct b from Book b",
                Book.class)
                .setHint(JAVAX_PERSISTENCE_FETCHGRAPH, graph)
                .getResultList();
    }

    @Override
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
