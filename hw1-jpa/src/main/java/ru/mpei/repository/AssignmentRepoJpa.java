package ru.mpei.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;
import ru.mpei.model.Assignment;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public class AssignmentRepoJpa implements AssignmentRepo {

    @PersistenceContext
    EntityManager em;

    @Override
    public void save(Assignment a) {
        if (a.getId() == 0) {
            em.persist(a);
        } else {
            em.merge(a);
        }
    }

    @Override
    public boolean delete(long id) {
        int count = em.createQuery("delete from Assignment a where a.id = :id")
                .setParameter("id", id)
                .executeUpdate();
        return count == 1;
    }

    @Override
    public Optional<Assignment> get(long id) {
        return Optional.ofNullable(em.find(Assignment.class, id));
    }

    @Override
    public List<Assignment> getAll() {
        return em.createQuery("select a from Assignment a", Assignment.class)
                .getResultList();
    }
}
