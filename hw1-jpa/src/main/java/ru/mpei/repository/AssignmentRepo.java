package ru.mpei.repository;

import org.springframework.stereotype.Repository;
import ru.mpei.model.Assignment;

import java.util.List;
import java.util.Optional;

@Repository
public interface AssignmentRepo {
    void save(Assignment s);

    boolean delete(long id);

    Optional<Assignment> get(long id);

    List<Assignment> getAll();
}
