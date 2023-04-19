package ru.mpei.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.mpei.model.Grade;

@Repository
public interface GradeRepo extends JpaRepository<Grade, Long> {
}
