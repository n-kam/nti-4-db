package ru.mpei;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import ru.mpei.model.Course;
import ru.mpei.model.Group;

import java.util.Map;

@SpringBootApplication
@Slf4j
public class Main {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(Main.class);
//        Console.main(args);

        AvgGradeByGroupByCourse avgGradeByGroupByCourse = context.getBean(AvgGradeByGroupByCourse.class);
        Map<Course, Map<Group, Double>> avgGradeByCourseByGroup = avgGradeByGroupByCourse.get();
//        System.out.println("\n\n\n" + avgGradeByCourseByGroup);
        System.out.println();


        for (Course course : avgGradeByCourseByGroup.keySet()) {

//            System.out.println("g:" + group.getName());
            Map<Group, Double> avgGradeByGroup = avgGradeByCourseByGroup.get(course);

            for (Group group : avgGradeByGroup.keySet()) {
//                System.out.println("c:" + course.getName());
//                System.out.println("avgg: " + avgGradeByCourse.get(course));
//                System.out.println(group.getName() + "'s average grade in " + course.getName() + ": " + avgGradeByCourse.get(course));
//                System.out.println(group.getName() + "'s average grade in " + course.getName() + ": " + avgGradeByGroup.get(group));
                System.out.println("Average grade in " + course.getName() + " for " + group.getName() + " is: " + avgGradeByGroup.get(group));

            }
        }
        System.out.println();
    }
}