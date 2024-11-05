package springmvc.starter.demo.repository.custom;

import springmvc.starter.demo.dto.StudentSearchRequest;
import springmvc.starter.demo.dto.response.StudentResponseDTO;
import springmvc.starter.demo.entity.StudentEntity;

import java.util.List;
import java.util.Map;

public interface StudentRepositoryCustom {
    List<StudentEntity> findAllDetails(Map<String, String> params);
}
