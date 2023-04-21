package ru.mpei.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.mpei.model.Assignment;

@Repository
public interface AssignmentRepo extends JpaRepository<Assignment, Long> {
}
