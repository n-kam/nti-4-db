package ru.mpei.DAO;

import ru.mpei.Domain.Grade;

import java.util.List;

public interface GradeDao {
    void insert(Grade grade);

    Grade getById(long id);

    List<Grade> getAll();

    void deleteById(long id);
}
