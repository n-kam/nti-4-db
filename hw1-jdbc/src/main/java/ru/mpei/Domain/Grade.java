package ru.mpei.Domain;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Grade {
    private long id;
    private long value;
    private Student student;
    private Assignment assignment;
}
