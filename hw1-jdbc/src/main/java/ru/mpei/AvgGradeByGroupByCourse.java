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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

        List<Course> courses = courseDao.extractFullModel();
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
                }
            }

            Map<Group, Double> avgGradeByGroup = new HashMap<>();

            for (Group group : gradesSumByGroup.keySet()) {
                double sum = gradesSumByGroup.get(group);
                int count = gradesCountByGroup.get(group);
                double avg = Double.parseDouble(df.format(sum / count));
                avgGradeByGroup.put(group, avg);
            }
            avgGradeByCourseByGroup.put(course, avgGradeByGroup);

        }
        return avgGradeByCourseByGroup;
    }
}

