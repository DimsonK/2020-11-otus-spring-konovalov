package ru.otus.spring.homework.spring06.repositories;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.homework.spring06.models.Genre;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

@Repository
public class GenreRepositoryJpa implements GenreRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public Genre save(Genre genre) {
        if (genre.getId() == 0) {
            em.persist(genre);
            return genre;
        } else {
            return em.merge(genre);
        }
    }

    @Override
    public Optional<Genre> findById(long id) {
        return Optional.ofNullable(em.find(Genre.class, id));
    }

    @Override
    public List<Genre> findAll() {
        TypedQuery<Genre> query = em.createQuery("select g from Genre g", Genre.class);
        return query.getResultList();
    }

    @Override
    public Optional<Genre> findByName(String name) {
        TypedQuery<Genre> query = em.createQuery(
                "select g from Genre g where g.name = :name",
                Genre.class);
        query.setParameter("name", name);
        return query.getResultList().stream().findFirst();
    }

    @Override
    @Transactional
    public void deleteById(long id) {
        findById(id).ifPresent(genre -> em.remove(genre));
    }

    @Override
    public long count() {
        Query query = em.createQuery("select count(g) from Genre g", Long.class);
        return (long) query.getSingleResult();
    }
}
