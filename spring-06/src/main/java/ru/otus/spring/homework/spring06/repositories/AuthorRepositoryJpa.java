package ru.otus.spring.homework.spring06.repositories;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.homework.spring06.models.Author;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

@Repository
public class AuthorRepositoryJpa implements AuthorRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public Author save(Author author) {
        if (author.getId() <= 0) {
            author.setId(getNextId());
            em.persist(author);
            return author;
        } else {
            return em.merge(author);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Author> findById(long id) {
        return Optional.ofNullable(em.find(Author.class, id));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Author> findAll() {
        TypedQuery<Author> query = em.createQuery("select a from Author a", Author.class);
        return query.getResultList();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Author> findByName(String name) {
        TypedQuery<Author> query = em.createQuery("" +
                        "select s from Author s where s.name = :name",
                Author.class);
        query.setParameter("name", name);
        return query.getResultList().stream().findFirst();
    }

    @Override
    @Transactional
    public void updateNameById(long id, String name) {
        Query query = em.createQuery(
                "update Author s set s.name = :name where s.id = :id"
        );
        query.setParameter("name", name);
        query.setParameter("id", id);
        query.executeUpdate();
    }

    @Override
    @Transactional
    public void deleteById(long id) {
        Query query = em.createQuery(
                "delete from Author s where s.id = :id"
        );
        query.setParameter("id", id);
        query.executeUpdate();
    }

    @Override
    @Transactional(readOnly = true)
    public long getNextId() {
        Query query = em.createNativeQuery("select next value for AUTHOR_SEQUENCE", Long.class);
        return query.getFirstResult();
    }

    @Override
    @Transactional(readOnly = true)
    public long count() {
        Query query = em.createQuery("select count(a) from Author a", Long.class);
        return (long) query.getSingleResult();
    }
}
