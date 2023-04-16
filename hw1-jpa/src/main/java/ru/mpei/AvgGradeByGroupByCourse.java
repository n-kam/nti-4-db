package ru.mpei;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.mpei.model.Assignment;
import ru.mpei.model.Course;
import ru.mpei.model.Grade;
import ru.mpei.model.Group;
import ru.mpei.repository.CourseRepo;
import ru.mpei.repository.GroupRepo;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AvgGradeByGroupByCourse {


    @Autowired
    private CourseRepo courseRepo;
    @Autowired
    private GroupRepo groupRepo;

    DecimalFormat df = new DecimalFormat("#.##");

    {
        df.setRoundingMode(RoundingMode.CEILING);
    }


    Map<Course, Map<Group, Double>> get() {

        List<Group> groups = groupRepo.getAll();
        List<Course> courses = courseRepo.getAll();

        Map<Course, Map<Group, Double>> avgGradeByCourseByGroup = new HashMap<>();

//        for (Group group : groups) {
        for (Course course : courses) {

            Map<Group, Double> avgGradeByGroup = new HashMap<>();

//            System.out.println("Group: " + group.getName());

//            for (Course course : courses) {
            for (Group group : groups) {


                double gradesSum = 0;
                int gradesQuantity = 0;

//                System.out.println("Course: " + course.getName());
                for (Assignment assignment : course.getAssignments()) {

//                    System.out.println("Ass: " + assignment.getName());

                    for (Grade grade : assignment.getGrades()) {

                        if ((group.getStudents().contains(grade.getStudent()))) {

                            gradesQuantity += 1;
                            gradesSum += grade.getValue();


//                            System.out.println("Student: " + grade.getStudent().getName() +
//                                    "; Group: " + group.getName() +
//                                    "; Course: " + grade.getAssignment().getCourse().getName() +
//                                    "; Course: " + course.getName() +
//                                    "; Assignment: " + grade.getAssignment().getName() +
//                                    "; Assignment: " + assignment.getName() +
//                                    "; Grade: " + grade.getValue());
                        }
                    }
                }

//                System.out.println(group.getName() + "'s average grade in " + course.getName() + ": " + gradesSum / gradesQuantity);
//                System.out.println("grades sum (debug): " + gradesSum);
                Double gradeRoundVal = Double.valueOf(df.format(gradesSum / gradesQuantity));
                avgGradeByGroup.put(group, gradeRoundVal);

            }
            avgGradeByCourseByGroup.put(course, avgGradeByGroup);

        }

        return avgGradeByCourseByGroup;
    }
}

