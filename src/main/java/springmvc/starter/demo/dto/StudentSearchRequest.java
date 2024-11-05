package springmvc.starter.demo.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class StudentSearchRequest {
    private Long id;
    private String fullname;
    private String majorId;
    private String schoolId;
    private String companyName;
    private Date jobTime;
}
