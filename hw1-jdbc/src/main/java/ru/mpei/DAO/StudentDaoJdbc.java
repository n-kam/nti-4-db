package ru.mpei.DAO;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;
import ru.mpei.Domain.Group;
import ru.mpei.Domain.Student;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class StudentDaoJdbc implements StudentDao {

    private final NamedParameterJdbcOperations jdbc;

    public StudentDaoJdbc(NamedParameterJdbcOperations jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public void insert(Student s) {
        jdbc.update("insert into students (id, name, group_id) values (:id, :n, :gid)",
                Map.of("id", s.getId(), "n", s.getName(), "gid", s.getGroup().getId()));
    }

    @Override
    public Student getById(long id) {
        return jdbc.queryForObject("select * from students where id=:id", Map.of("id", id), new StudentMapper());
    }

    @Override
    public List<Student> getAll() {
        return jdbc.query("select * from students", new StudentMapper());
    }

    @Override
    public void deleteById(long id) {
        jdbc.update("delete from students where id=:id", Map.of("id", id));
    }

    private static class StudentMapper implements RowMapper<Student> {

        @Override
        public Student mapRow(ResultSet rs, int rowNum) throws SQLException {
            Group group = new Group();
            group.setId(rs.getLong("group_id"));
            return new Student(rs.getLong("id"),
                    rs.getString("name"),
                    group, new ArrayList<>());
        }
    }
}
