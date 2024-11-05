package springmvc.starter.demo.dto.request;


import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Getter
@Setter
public class StudentDTO {
    private Long id;

    @NotBlank(message = "Tên sinh viên không được để trống")
    private String fullname;

    @NotBlank(message = "Gmail không được để trống")
    private String email;
    private String phoneNumber;

    @NotBlank(message = "Địa chỉ không được để trống")
    private String address;

    @NotNull(message = "Bạn chưa chọn trường")
    private Long schoolId;

    @NotNull(message = "Bạn chưa chọn ngành")
    private Long majorId;
    private String graduationSystem;

    @NotNull(message = "Ngày tốt nghiệp không được để trống")
    @Future(message = "Ngày tốt nghiệp phải là một ngày trong tương lai")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date graduationDate;
    private String graduationType;
}
