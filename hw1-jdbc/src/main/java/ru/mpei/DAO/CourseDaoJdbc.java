package ru.mpei.DAO;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;
import ru.mpei.Domain.*;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.*;

@Repository
public class CourseDaoJdbc {

    //    private final JdbcOperations jdbc;
    private final NamedParameterJdbcOperations npjo;

    public CourseDaoJdbc(NamedParameterJdbcOperations npjo) {
//        this.jdbc = namedParameterJdbcOperations.getJdbcOperations();
        this.npjo = npjo;
    }

//    public void insert(Student student) {
//        namedParameterJdbcOperations.update("insert into persons (id, name) values (:id, :name)",
//                Map.of("id", person.getId(), "name", person.getName()));
//    }

//    public Student getById(long id) {
//        Map<String, Object> params = Collections.singletonMap("id", id);
//        return npjo.queryForObject(
//                "select id, name from courses where id = :id", params, new StudentMapper()
//        );
//    }


    public Course getById(long id) {
        Map<String, Object> params = Collections.singletonMap("id", id);

        return Objects.requireNonNull(npjo.query("select c.id as c_id, c.name as c_name, a.id as a_id, " +
                "a.name as a_name, course_id, gd.id as gd_id, g_value, student_id, " +
                "assignment_id, s.name as s_name, group_id, gp.name as gp_name " +
                "from courses c " +
                "where id=:id " +
                "left join assignments a on c.id=a.course_id " +
                "left join grades gd on gd.assignment_id = a.id " +
                "left join students s on gd.student_id = s.id " +
                "left join groups gp on s.group_id = gp.id", params, new CourseRse())).get(0);
    }

    public List<Course> getAll() {
        return npjo.query("select c.id as c_id, c.name as c_name, a.id as a_id, " +
                "a.name as a_name, course_id, gd.id as gd_id, g_value, student_id, " +
                "assignment_id, s.name as s_name, group_id, gp.name as gp_name " +
                "from courses c " +
                "left join assignments a on c.id=a.course_id " +
                "left join grades gd on gd.assignment_id = a.id " +
                "left join students s on gd.student_id = s.id " +
                "left join groups gp on s.group_id = gp.id", new CourseRse());
    }

    //    public void deleteById(long id) {
//        Map<String, Object> params = Collections.singletonMap("id", id);
//        namedParameterJdbcOperations.update(
//                "delete from persons where id = :id", params
//        );
//    }
    private static class CourseRse implements ResultSetExtractor<List<Course>> {

        @Override
        public List<Course> extractData(ResultSet rs) throws SQLException, DataAccessException {

            HashMap<Long, Group> groups = new HashMap<>();
            HashMap<Long, Student> students = new HashMap<>();
            HashMap<Long, Grade> grades = new HashMap<>();
            HashMap<Long, Assignment> assignments = new HashMap<>();
            HashMap<Long, Course> courses = new HashMap<>();

            ResultSetMetaData metadata = rs.getMetaData();
            int columnCount = metadata.getColumnCount();
            String labels = "";
            for (int i = 1; i <= columnCount; i++) {
                labels += metadata.getColumnLabel(i) + ", ";
            }
            System.out.println("labels: " + labels);

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

//                student.setGroup(group);
//                group.getStudents().add(student);

//                grade.setStudent(student);
//                student.getGrades().add(grade);

//                grade.setAssignment(a);
//                a.getGrades().add(grade);

//                a.setCourse(course);
//                course.getAssignments().add(a);


                String row = "";
                for (int i = 1; i <= columnCount; i++) {
                    row += rs.getString(i) + ", ";
                }
                System.out.println(row);

            }

            return new ArrayList<>(courses.values());
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
