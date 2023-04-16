package ru.mpei.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;
import ru.mpei.model.Course;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public class CourseRepoJpa implements CourseRepo{

    @PersistenceContext
    EntityManager em;

    @Override
    public void save(Course c){
        if (c.getId() == 0) {
            em.persist(c);
        } else {
            em.merge(c);
        }
    }

    @Override
    public boolean delete(long id) {
        int count = em.createQuery("delete from Course c where c.id = :id")
                .setParameter("id", id)
                .executeUpdate();
        return count == 1;
    }

    @Override
    public Optional<Course> get(long id) {
        return Optional.ofNullable(em.find(Course.class, id));
    }

    @Override
    public List<Course> getAll() {
        return em.createQuery("select c from Course c", Course.class)
                .getResultList();
    }
}
