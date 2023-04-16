package ru.mpei.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.List;

@Data
@Entity
@Table(name = "assignments")
@NoArgsConstructor
@AllArgsConstructor
public class Assignment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name", nullable = false)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @Fetch(FetchMode.JOIN)
    @JoinColumn(name = "course_id")
    private Course course;

    @Override
    public String toString() {
        return "Assignment{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", courseName=" + course.getName() +
                '}';
    }


    @OneToMany(fetch = FetchType.LAZY, mappedBy = "assignment", cascade = CascadeType.ALL)
    @Fetch(FetchMode.JOIN)
    private List<Grade> grades;
}
