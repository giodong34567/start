package springmvc.starter.demo.vo;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClassVO {

    @NotBlank(message = "Tên lớp không được để trống")
    private String name;

    private String description;
}
