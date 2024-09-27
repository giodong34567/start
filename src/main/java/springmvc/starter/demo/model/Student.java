package springmvc.starter.demo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "student")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, name = "name", columnDefinition = "VARCHAR(50)")
    private String name;

    @Column(nullable = false, name = "email", unique = true, columnDefinition = "VARCHAR(50)")
    private String email;

    @Column(nullable = false, name = "age", columnDefinition = "INT", length = 3)
    private int age;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "classid")
    private ClassEntity classEntity;

    @OneToMany(mappedBy = "student", fetch = FetchType.LAZY)
    private List<Mark> marks = new ArrayList<>();
}

