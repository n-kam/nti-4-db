package ru.mpei.DAO;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;
import ru.mpei.Domain.Student;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class StudentDaoJdbc {

    //    private final JdbcOperations jdbc;
    private final NamedParameterJdbcOperations npjo;

    public StudentDaoJdbc(NamedParameterJdbcOperations npjo) {
//        this.jdbc = namedParameterJdbcOperations.getJdbcOperations();
        this.npjo = npjo;
    }

//    public void insert(Student student) {
//        namedParameterJdbcOperations.update("insert into persons (id, name) values (:id, :name)",
//                Map.of("id", person.getId(), "name", person.getName()));
//    }

    public Student getById(long id) {
        Map<String, Object> params = Collections.singletonMap("id", id);
        return npjo.queryForObject(
                "select id, name from students where id = :id", params, new StudentMapper()
        );
    }

    public List<Student> getAll() {
        return npjo.query("select id, name from students", new StudentMapper());
    }

//    public void deleteById(long id) {
//        Map<String, Object> params = Collections.singletonMap("id", id);
//        namedParameterJdbcOperations.update(
//                "delete from persons where id = :id", params
//        );
//    }
    private static class StudentRS implements ResultSetExtractor<Student>{

    @Override
    public Student extractData(ResultSet rs) throws SQLException, DataAccessException {
        HashMap<Long, Student> studentMap = new HashMap<>();
        while (rs.next()){

        }
        Student student = new Student();

        rs.getLong("id");
        return null;
    }
}

    private static class StudentMapper implements RowMapper<Student> {

        @Override
        public Student mapRow(ResultSet resultSet, int i) throws SQLException {
            long id = resultSet.getLong("id");
            String name = resultSet.getString("name");
            Student student = new Student();
            student.setId(id);
            student.setName(name);
            return student;
        }
    }
}
