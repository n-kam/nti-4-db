package ru.mpei;

import lombok.extern.slf4j.Slf4j;
import org.h2.tools.Console;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import ru.mpei.DAO.*;
import ru.mpei.Domain.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

@SpringBootApplication
@Slf4j
public class Main {
    public static void main(String[] args) throws SQLException {
        ConfigurableApplicationContext context = SpringApplication.run(Main.class);
//        Console.main(args);

        AvgGradeByGroupByCourse avgGradeByGroupByCourse = context.getBean(AvgGradeByGroupByCourse.class);
        Map<Course, Map<Group, Double>> courseMapMap = avgGradeByGroupByCourse.get();
        System.out.println();

        for (Course course : courseMapMap.keySet()){
            for (Group group : courseMapMap.get(course).keySet()){
                Double avg = courseMapMap.get(course).get(group);
                System.out.println("Average grade in " + course.getName() + " for " + group.getName() + " is: " + avg);
            }
        }
        System.out.println();

        CourseDao courseDao = context.getBean(CourseDaoJdbc.class);
//
//        courseDao.insert(new Course(4, "new", new ArrayList<>()));
//        for (Course course : courseDao.getAll()) {
//            System.out.println("Course id: " + course.getId() + "; name: " + course.getName() + "; ass-s:");
//            for (Assignment assignment : course.getAssignments()) {
//                System.out.println(assignment.getId() + "; " + assignment.getName());
//            }
//        }
//        Course course = courseDao.getById(4);
//        System.out.println("Course id: " + course.getId() + "; name: " + course.getName() + "; ass-s:");
//        courseDao.deleteById(4);
//
//        for (Course c : courseDao.getAll()) {
//            System.out.println("Course id: " + c.getId() + "; name: " + c.getName() + "; ass-s:");
//            for (Assignment assignment : c.getAssignments()) {
//                System.out.println(assignment.getId() + "; " + assignment.getName());
//            }
//        }

        AssignmentDao aDao = context.getBean(AssignmentDao.class);
//        System.out.println(aDao.getById(1));
//        System.out.println(aDao.getAll());
//        aDao.insert(new Assignment(7, "ass", new Course(1), new ArrayList<>()));
//        System.out.println(aDao.getAll());
//        aDao.deleteById(7);
//        System.out.println(aDao.getAll());


        GradeDao gradeDao = context.getBean(GradeDao.class);
//        Assignment assignment = new Assignment(7, "ass", new Course(1), new ArrayList<>());
//        aDao.insert(assignment);
//
//        Student student = new Student();
//        student.setId(2);
//        student.setName("name");
//
//        Grade grade = new Grade(100, 200, student, assignment);
//        gradeDao.insert(grade);
//
//        System.out.println();
//        for (Grade grade1 : gradeDao.getAll()) {
//            System.out.println("id: " + grade1.getId() + "; v: "
//                    + grade1.getValue() + "; stud id: " + grade1.getStudent().getId()
//                    + "; a id: " + grade1.getAssignment().getId());
//        }
//
//        Grade grade3 = gradeDao.getById(100);
//        System.out.println("id: " + grade3.getId() + "; v: "
//                + grade3.getValue() + "; stud id: " + grade3.getStudent().getId()
//                + "; a id: " + grade3.getAssignment().getId());
//        gradeDao.deleteById(100);
//        for (Grade grade1 : gradeDao.getAll()) {
//            System.out.println("id: " + grade1.getId() + "; v: "
//                    + grade1.getValue() + "; stud id: " + grade1.getStudent().getId()
//                    + "; a id: " + grade1.getAssignment().getId());
//        }

        GroupDao groupDao = context.getBean(GroupDao.class);
//        Group hufflepuff = new Group(3, "Hufflepuff", 1991, new ArrayList<>());
//        System.out.println("\nAll:");
//        for (Group group : groupDao.getAll()) {
//            System.out.println("id: " + group.getId() + " name: " + group.getName() + " y: " + group.getYear());
//        }
//        groupDao.insert(hufflepuff);
//        Group group = groupDao.getById(3);
//
//        System.out.println("\n3:");
//        System.out.println("id: " + group.getId() + " name: " + group.getName() + " y: " + group.getYear());
//        groupDao.deleteById(3);
//
//        System.out.println("\nAll after delete:");
//        for (Group group2 : groupDao.getAll()) {
//            System.out.println("id: " + group2.getId() + " name: " + group2.getName() + " y: " + group2.getYear());
//        }

        StudentDao studentDao = context.getBean(StudentDao.class);
//        System.out.println(studentDao.getAll());
//        Group group = new Group();
//        group.setId(1);
//        studentDao.insert(new Student(200, "Peeves", group, new ArrayList<>()));
//        System.out.println(studentDao.getById(200));
//        studentDao.deleteById(200);
//        System.out.println(studentDao.getAll());

    }
}