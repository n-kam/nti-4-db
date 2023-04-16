package ru.mpei.repository;

import org.springframework.stereotype.Repository;
import ru.mpei.model.Group;

import java.util.List;
import java.util.Optional;

@Repository
public interface GroupRepo {
    void save(Group g);

    boolean delete(long id);

    Optional<Group> get(long id);

    List<Group> getAll();
}
