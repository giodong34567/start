package springmvc.starter.demo.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "student")
public class StudentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "fullname")
    private String fullname;

    @Column(name = "email")
    private String email;

    @Column(name = "phonenumber")
    private String phonenumber;

    @Column(name = "address")
    private String address;

    @OneToMany(mappedBy = "student", fetch = FetchType.LAZY)
    List<GraduationEntity> graduations = new ArrayList<>();

    @OneToMany(mappedBy = "student", fetch = FetchType.LAZY)
    List<JobEntity> jobs = new ArrayList<>();
}
