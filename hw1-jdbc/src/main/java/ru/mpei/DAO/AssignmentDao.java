package ru.mpei.DAO;

import ru.mpei.Domain.Assignment;

import java.util.List;

public interface AssignmentDao {
    void insert(Assignment assignment);

    Assignment getById(long id);

    List<Assignment> getAll();

    void deleteById(long id);
}
