package ru.mpei.DAO;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;
import ru.mpei.Domain.Assignment;
import ru.mpei.Domain.Grade;
import ru.mpei.Domain.Student;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Repository
public class GradeDaoJdbc implements GradeDao {

    private final NamedParameterJdbcOperations jdbc;

    public GradeDaoJdbc(NamedParameterJdbcOperations jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public void insert(Grade g) {
        jdbc.update("insert into grades (id, g_value, student_id, assignment_id) values (:id, :v, :s_id, :a_id)",
                Map.of("id", g.getId(), "v", g.getValue(), "s_id", g.getStudent().getId(), "a_id", g.getAssignment().getId()));
    }

    @Override
    public Grade getById(long id) {
        return jdbc.queryForObject("select * from grades where id=:id", Map.of("id", id), new GradeMapper());
    }

    @Override
    public List<Grade> getAll() {
        return jdbc.query("select * from grades", new GradeMapper());
    }

    @Override
    public void deleteById(long id) {
        jdbc.update("delete from grades where id=:id", Map.of("id", id));
    }

    private static class GradeMapper implements RowMapper<Grade> {

        @Override
        public Grade mapRow(ResultSet rs, int rowNum) throws SQLException {
            Assignment assignment = new Assignment();
            assignment.setId(rs.getLong("assignment_id"));
            Student student = new Student();
            student.setId(rs.getLong("student_id"));
            long value = rs.getLong("g_value");

            return new Grade(rs.getLong("id"), value, student, assignment);
        }
    }
}
