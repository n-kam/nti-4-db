package ru.mpei.repository;

import org.springframework.stereotype.Repository;
import ru.mpei.model.Course;

import java.util.List;
import java.util.Optional;

@Repository
public interface CourseRepo {
    void save(Course s);

    boolean delete(long id);

    Optional<Course> get(long id);

    List<Course> getAll();
}
