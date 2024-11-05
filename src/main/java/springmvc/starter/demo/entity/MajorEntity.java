package springmvc.starter.demo.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "major")
public class MajorEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "majorname")
    private String majorName;

    @Column(name = "majortype")
    private String majorType;

    @OneToMany(mappedBy = "major", fetch = FetchType.LAZY)
    private List<JobEntity> jobs = new ArrayList<JobEntity>();

    @OneToMany(mappedBy = "major", fetch = FetchType.LAZY)
    private List<GraduationEntity> graduations = new ArrayList<>();
}
