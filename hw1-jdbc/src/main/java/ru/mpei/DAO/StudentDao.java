package ru.mpei.DAO;

import ru.mpei.Domain.Student;

import java.util.List;

public interface StudentDao {
    void insert(Student student);

    Student getById(long id);

    List<Student> getAll();

    void deleteById(long id);
}
