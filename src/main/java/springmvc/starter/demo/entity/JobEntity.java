package springmvc.starter.demo.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
@Table(name = "job")
public class JobEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "joinedtime")
    private Date joinedTime;

    @Column(name = "jobname")
    private String jobName;

    @Column(name = "companyname")
    private String companyName;

    @Column(name = "companyaddress")
    private String companyAddress;

    @Column(name = "jobtime")
    private String jobTime;

    @ManyToOne
    @JoinColumn(name = "studentid")
    private StudentEntity student;

    @ManyToOne
    @JoinColumn(name = "majorid")
    private MajorEntity major;

}
