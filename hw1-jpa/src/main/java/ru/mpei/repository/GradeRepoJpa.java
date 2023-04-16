package ru.mpei.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;
import ru.mpei.model.Grade;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public class GradeRepoJpa implements GradeRepo{

    @PersistenceContext
    EntityManager em;

    @Override
    public void save(Grade g){
        if (g.getId() == 0) {
            em.persist(g);
        } else {
            em.merge(g);
        }
    }

    @Override
    public boolean delete(long id) {
        int count = em.createQuery("delete from Grade g where g.id = :id")
                .setParameter("id", id)
                .executeUpdate();
        return count == 1;
    }

    @Override
    public Optional<Grade> get(long id) {
        return Optional.ofNullable(em.find(Grade.class, id));
    }

    @Override
    public List<Grade> getAll() {
        return em.createQuery("select g from Grade g", Grade.class)
                .getResultList();
    }

}
