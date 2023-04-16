package ru.mpei.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;
import ru.mpei.model.Group;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public class GroupRepoJpa implements GroupRepo{

    @PersistenceContext
    EntityManager em;

    @Override
    public void save(Group g){
        if (g.getId() == 0) {
            em.persist(g);
        } else {
            em.merge(g);
        }
    }

    @Override
    public boolean delete(long id) {
        int count = em.createQuery("delete from Group g where g.id = :id")
                .setParameter("id", id)
                .executeUpdate();
        return count == 1;
    }

    @Override
    public Optional<Group> get(long id) {
        return Optional.ofNullable(em.find(Group.class, id));
    }

    @Override
    public List<Group> getAll() {
        return em.createQuery("select g from Group g", Group.class)
                .getResultList();
    }

}
