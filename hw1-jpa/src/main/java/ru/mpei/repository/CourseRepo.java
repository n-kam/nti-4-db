package ru.mpei.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.mpei.model.Course;

public interface CourseRepo extends JpaRepository<Course, Long> {
}
