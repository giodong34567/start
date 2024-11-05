package springmvc.starter.demo.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class GraduationResponseDTO {
    private Long id;
    private Long majorId;
    private Long schoolId;
    private Long studentId;
    private String graduationSystem;
    private Date graduationDate;
    private String graduationType;
}
