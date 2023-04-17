package ru.mpei.Domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class Group {
    private long id;
    private String name;
    private List<Student> students;
}
