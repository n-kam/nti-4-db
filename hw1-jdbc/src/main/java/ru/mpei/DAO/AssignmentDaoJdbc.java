package ru.mpei.DAO;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;
import ru.mpei.Domain.Assignment;
import ru.mpei.Domain.Course;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class AssignmentDaoJdbc implements AssignmentDao {

    private final NamedParameterJdbcOperations jdbc;

    public AssignmentDaoJdbc(NamedParameterJdbcOperations jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public void insert(Assignment a) {
        jdbc.update("insert into assignments (id, name, course_id) values (:id, :name, :c_id)",
                Map.of("id", a.getId(), "name", a.getName(), "c_id", a.getCourse().getId()));
    }

    @Override
    public Assignment getById(long id) {
        return jdbc.queryForObject("select * from assignments where id=:id", Map.of("id", id), new AssignmentMapper());
    }

    @Override
    public List<Assignment> getAll() {
        return jdbc.query("select * from assignments", new AssignmentMapper());
    }

    @Override
    public void deleteById(long id) {
        jdbc.update("delete from assignments where id=:id", Map.of("id", id));

    }

    private static class AssignmentMapper implements RowMapper<Assignment> {

        @Override
        public Assignment mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Assignment(rs.getLong("id"),
                    rs.getString("name"),
                    new Course(rs.getLong("course_id")),
                    new ArrayList<>());
        }
    }
}
