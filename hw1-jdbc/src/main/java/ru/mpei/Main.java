package ru.mpei;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import ru.mpei.Domain.Course;
import ru.mpei.Domain.Group;

import java.util.Map;

@SpringBootApplication
@Slf4j
public class Main {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(Main.class);
//        Console.main(args);

        AvgGradeByGroupByCourse avgGradeByGroupByCourse = context.getBean(AvgGradeByGroupByCourse.class);
        Map<Course, Map<Group, Double>> courseMapMap = avgGradeByGroupByCourse.get();
        System.out.println();

        for (Course course : courseMapMap.keySet()) {
            for (Group group : courseMapMap.get(course).keySet()) {
                Double avg = courseMapMap.get(course).get(group);
                System.out.println("Average grade in " + course.getName() + " for " + group.getName() + " is: " + avg);
            }
        }
        System.out.println();
    }
}