package ru.mpei.Domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Grade {
    private long id;
    private long value;
    private Student student;
    private Assignment assignment;
}
