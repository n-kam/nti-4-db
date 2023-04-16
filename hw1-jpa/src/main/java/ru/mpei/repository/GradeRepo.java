package ru.mpei.repository;

import org.springframework.stereotype.Repository;
import ru.mpei.model.Grade;

import java.util.List;
import java.util.Optional;

@Repository
public interface GradeRepo {
    void save(Grade g);

    boolean delete(long id);

    Optional<Grade> get(long id);

    List<Grade> getAll();
}
