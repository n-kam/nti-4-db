package ru.mpei.service;

import lombok.RequiredArgsConstructor;
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
@RequiredArgsConstructor
public class AvgGradeByGroupByCourse {

    private final CourseRepo courseRepo;
    private final GroupRepo groupRepo;

    DecimalFormat df = new DecimalFormat("#.##");

    {
        df.setRoundingMode(RoundingMode.CEILING);
    }

    public Map<Course, Map<Group, Double>> get() {

        List<Group> groups = groupRepo.findAll();
        List<Course> courses = courseRepo.findAll();

        Map<Course, Map<Group, Double>> avgGradeByCourseByGroup = new HashMap<>();

        for (Course course : courses) {

            Map<Group, Double> avgGradeByGroup = new HashMap<>();

            for (Group group : groups) {
                double gradesSum = 0;
                int gradesQuantity = 0;

                for (Assignment assignment : course.getAssignments()) {
                    for (Grade grade : assignment.getGrades()) {
                        if ((group.getStudents().contains(grade.getStudent()))) {

                            gradesQuantity += 1;
                            gradesSum += grade.getValue();
                        }
                    }
                }

                double gradeRoundVal = Double.parseDouble(df.format(gradesSum / gradesQuantity));
                avgGradeByGroup.put(group, gradeRoundVal);
            }
            avgGradeByCourseByGroup.put(course, avgGradeByGroup);
        }
        return avgGradeByCourseByGroup;
    }
}

