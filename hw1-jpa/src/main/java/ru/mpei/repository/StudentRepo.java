package ru.mpei.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.mpei.model.Student;

@Repository
public interface StudentRepo extends JpaRepository<Student, Long> {
}
