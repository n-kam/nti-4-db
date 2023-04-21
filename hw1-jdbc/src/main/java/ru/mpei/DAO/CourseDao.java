package ru.mpei.DAO;

import ru.mpei.Domain.Course;

import java.util.List;

public interface CourseDao {
    void insert(Course person);

    Course getById(long id);

    List<Course> getAll();

    void deleteById(long id);
}
