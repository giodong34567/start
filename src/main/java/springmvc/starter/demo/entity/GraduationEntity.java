package springmvc.starter.demo.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
@Table(name = "graduation")
public class GraduationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "graduationsystem")
    private String graduationSystem;

    @Column(name = "graduationdate")
    private Date graduationDate;

    @Column(name = "graduationtype")
    private String graduationType;

    @ManyToOne
    @JoinColumn(name = "studentid")
    private StudentEntity student;

    @ManyToOne
    @JoinColumn(name = "schoolid")
    private SchoolEntity school;

    @ManyToOne
    @JoinColumn(name = "majorid")
    private MajorEntity major;
}
