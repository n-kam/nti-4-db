package ru.mpei.Domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class Assignment {
    private long id;
    private String name;
    private Course course;
    private List<Grade> grades;

    @Override
    public String toString() {
        return "Assignment{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", courseName=" + course.getName() +
                '}';
    }
}
