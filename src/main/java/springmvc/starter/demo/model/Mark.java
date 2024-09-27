package springmvc.starter.demo.model;

import jakarta.persistence.*;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Cleanup;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "mark")
public class Mark {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "mark")
    private Double mark;

    @Column(name = "marktype")
    private String markType;

    @ManyToOne
    @JoinColumn(name = "studentid")
    private Student student;

    @ManyToOne
    @JoinColumn(name = "subjectid")
    private Subject subject;
}
