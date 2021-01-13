package ru.otus.spring.homework.spring06.repositories;

import org.springframework.stereotype.Repository;
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
    public Author save(Author author) {
        if (author.getId() == 0) {
            em.persist(author);
            return author;
        } else {
            return em.merge(author);
        }
    }

    @Override
    public Optional<Author> findById(long id) {
        return Optional.ofNullable(em.find(Author.class, id));
    }

    @Override
    public List<Author> findAll() {
        TypedQuery<Author> query = em.createQuery("select a from Author a", Author.class);
        return query.getResultList();
    }

    @Override
    public Optional<Author> findByName(String name) {
        TypedQuery<Author> query = em.createQuery("" +
                        "select s from Author s where s.name = :name",
                Author.class);
        query.setParameter("name", name);
        return query.getResultList().stream().findFirst();
    }

    @Override
    public void deleteById(long id) {
        findById(id).ifPresent(author -> em.remove(author));
    }

    @Override
    public long count() {
        Query query = em.createQuery("select count(a) from Author a", Long.class);
        return (long) query.getSingleResult();
    }
}
