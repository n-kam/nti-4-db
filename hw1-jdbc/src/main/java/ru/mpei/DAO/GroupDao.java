package ru.mpei.DAO;

import ru.mpei.Domain.Group;

import java.util.List;

public interface GroupDao {
    void insert(Group group);

    Group getById(long id);

    List<Group> getAll();

    void deleteById(long id);
}
