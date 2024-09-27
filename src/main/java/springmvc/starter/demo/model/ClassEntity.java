package springmvc.starter.demo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "class")
public class ClassEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, name = "name", unique = true, columnDefinition = "VARCHAR(50)")
    private String name;

    @Column(nullable = false, name = "description", columnDefinition = "TINYTEXT")
    private String description;

    @OneToMany(mappedBy = "classEntity", targetEntity = Student.class)
    private List<Student> students;
}
