package ru.mpei.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;
import ru.mpei.model.Student;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public class StudentRepoJpa implements StudentRepo {

    @PersistenceContext
    EntityManager em;

    @Override
    public void save(Student s) {
        if (s.getId() == 0) {
            em.persist(s);
        } else {
            em.merge(s);
        }
    }

    @Override
    public boolean delete(long id) {
        int count = em.createQuery("delete from Student s where s.id = :id")
                .setParameter("id", id)
                .executeUpdate();
        return count == 1;
    }

    @Override
    public Optional<Student> get(long id) {
        return Optional.ofNullable(em.find(Student.class, id));
    }

    @Override
    public List<Student> getAll() {
        return em.createQuery("select s from Student s", Student.class)
                .getResultList();
    }
}
