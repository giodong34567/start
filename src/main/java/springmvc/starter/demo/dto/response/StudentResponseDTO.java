package springmvc.starter.demo.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class StudentResponseDTO {
    private Long id;
    private String address;
    private String email;
    private String fullname;
    private String phoneNumber;
    private Long majorId;
    private Long schoolId;
    private String companyName;
    private Date jobTime;
}
