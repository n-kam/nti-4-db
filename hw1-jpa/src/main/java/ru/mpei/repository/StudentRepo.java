package ru.mpei.repository;

import org.springframework.stereotype.Repository;
import ru.mpei.model.Student;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepo {
    void save(Student s);

    boolean delete(long id);

    Optional<Student> get(long id);

    List<Student> getAll();
}
