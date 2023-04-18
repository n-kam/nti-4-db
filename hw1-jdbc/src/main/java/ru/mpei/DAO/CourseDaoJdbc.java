package ru.mpei.DAO;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;
import ru.mpei.Domain.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

@Repository
public class CourseDaoJdbc implements CourseDao {

    private final NamedParameterJdbcOperations jdbc;

    public CourseDaoJdbc(NamedParameterJdbcOperations jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public void insert(Course course) {
        jdbc.update("insert into courses (id, name) values (:id, :name)",
                Map.of("id", course.getId(), "name", course.getName()));
    }

    @Override
    public Course getById(long id) {
        return jdbc.queryForObject("select * from courses where id=:id", Map.of("id", id), new CourseMapper());
    }

    @Override
    public List<Course> getAll() {
        return jdbc.query("select * from courses", new CourseMapper());
    }

    @Override
    public void deleteById(long id) {
        jdbc.update("delete from courses where id=:id", Map.of("id", id));

    }

    public List<Course> extractFullModel() {
        return jdbc.query("select c.id as c_id, c.name as c_name, a.id as a_id, " +
                "a.name as a_name, course_id, gd.id as gd_id, g_value, student_id, " +
                "assignment_id, s.name as s_name, group_id, gp.name as gp_name " +
                "from courses c " +
                "left join assignments a on c.id=a.course_id " +
                "left join grades gd on gd.assignment_id = a.id " +
                "left join students s on gd.student_id = s.id " +
                "left join groups gp on s.group_id = gp.id", new CourseFullModelRse());
    }

    private static class CourseMapper implements RowMapper<Course> {

        @Override
        public Course mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Course(rs.getLong("id"),
                    rs.getString("name"),
                    new ArrayList<>());
        }
    }

    private static class CourseFullModelRse implements ResultSetExtractor<List<Course>> {

        @Override
        public List<Course> extractData(ResultSet rs) throws SQLException, DataAccessException {

            HashMap<Long, Group> groups = new HashMap<>();
            HashMap<Long, Student> students = new HashMap<>();
            HashMap<Long, Grade> grades = new HashMap<>();
            HashMap<Long, Assignment> assignments = new HashMap<>();
            HashMap<Long, Course> courses = new HashMap<>();

            while (rs.next()) {

                long groupId = rs.getLong("group_id");
                Group group;
                if (!groups.containsKey(groupId)) {
                    group = new Group();
                    group.setId(groupId);
                    group.setName(rs.getString("gp_name"));
                    group.setStudents(new ArrayList<>());
                    groups.put(groupId, group);
                } else group = groups.get(groupId);

                long studentId = rs.getLong("student_id");
                Student student;
                if (!students.containsKey(studentId)) {
                    student = new Student();
                    student.setId(studentId);
                    student.setName(rs.getString("s_name"));
                    student.setGrades(new ArrayList<>());
                    group.getStudents().add(student);
                    student.setGroup(group);
                    students.put(studentId, student);
                } else student = students.get(studentId);

                long courseId = rs.getLong("c_id");
                Course course;
                if (!courses.containsKey(courseId)) {
                    course = new Course();
                    course.setId(courseId);
                    course.setName(rs.getString("c_name"));
                    course.setAssignments(new ArrayList<>());
                    courses.put(courseId, course);
                } else course = courses.get(courseId);

                long aId = rs.getLong("a_id");
                Assignment a;
                if (!assignments.containsKey(aId)) {
                    a = new Assignment();
                    a.setId(aId);
                    a.setName(rs.getString("a_name"));
                    a.setGrades(new ArrayList<>());
                    a.setCourse(course);
                    course.getAssignments().add(a);
                    assignments.put(aId, a);
                } else a = assignments.get(aId);


                long gradeId = rs.getLong("gd_id");
                Grade grade;
                if (!grades.containsKey(gradeId)) {
                    grade = new Grade();
                    grade.setId(gradeId);
                    grade.setValue(rs.getLong("g_value"));
                    grade.setStudent(student);
                    student.getGrades().add(grade);
                    grade.setAssignment(a);
                    a.getGrades().add(grade);
                    grades.put(gradeId, grade);
                } else grade = grades.get(gradeId);
            }
            return new ArrayList<>(courses.values());
        }
    }
}
