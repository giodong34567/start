package springmvc.starter.demo.dto.response;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SchoolResponseDTO {
    private Long id;
    private String name;
    private String address;
    private String phoneNumber;
}
