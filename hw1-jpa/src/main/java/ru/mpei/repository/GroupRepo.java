package ru.mpei.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.mpei.model.Group;

@Repository
public interface GroupRepo extends JpaRepository<Group, Long> {
}
