package ru.mpei;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.mpei.DAO.CourseDaoJdbc;
import ru.mpei.Domain.Assignment;
import ru.mpei.Domain.Course;
import ru.mpei.Domain.Grade;
import ru.mpei.Domain.Group;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.*;

@Service
public class AvgGradeByGroupByCourse {


    DecimalFormat df = new DecimalFormat("#.##");

    {
        df.setRoundingMode(RoundingMode.CEILING);
    }

    @Autowired
    CourseDaoJdbc courseDao;

    Map<Course, Map<Group, Double>> get() {

        Map<Course, Map<Group, Double>> avgGradeByCourseByGroup = new HashMap<>();

        List<Course> courses = courseDao.getAll();
        for (Course course : courses) {

            Map<Group, Double> gradesSumByGroup = new HashMap<>();
            Map<Group, Integer> gradesCountByGroup = new HashMap<>();

            for (Assignment assignment : course.getAssignments()) {
                for (Grade grade : assignment.getGrades()) {

                    Group group = grade.getStudent().getGroup();

                    double sum;
                    if (gradesSumByGroup.containsKey(group)) sum = gradesSumByGroup.get(group) + grade.getValue();
                    else sum = grade.getValue();
                    gradesSumByGroup.put(group, sum);

                    int currCount = 1;

                    if (gradesCountByGroup.containsKey(group)) currCount += gradesCountByGroup.get(group);

                    gradesCountByGroup.put(group, currCount);


//                    System.out.println("Course: " + course.getName() +
//                            ", Ass: " + assignment.getName() +
//                            ", Stud: " + grade.getStudent().getName() +
//                            ", Group: " + grade.getStudent().getGroup().getName() +
//                            ", Val: " + grade.getValue());

                }
            }

            Map<Group, Double> avgGradeByGroup = new HashMap<>();

            for (Group group : gradesSumByGroup.keySet()) {
                double sum = gradesSumByGroup.get(group);
                int count = gradesCountByGroup.get(group);
//                Double avg = Double.valueOf(df)
                double avg = Double.parseDouble(df.format(sum / count));
                avgGradeByGroup.put(group, avg);
//                System.out.println("DBG: Course: " + course.getName() + ", group: " + group.getName() + ", avg: " + avg);
            }
            avgGradeByCourseByGroup.put(course, avgGradeByGroup);

        }
        return avgGradeByCourseByGroup;
    }

}

