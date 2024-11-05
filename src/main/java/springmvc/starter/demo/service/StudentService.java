package springmvc.starter.demo.service;

import springmvc.starter.demo.dto.request.StudentDTO;
import springmvc.starter.demo.dto.response.StudentResponseDTO;

import java.util.List;
import java.util.Map;

public interface StudentService {
    List<StudentResponseDTO> findAll();
    void saveStudent(StudentDTO studentDTO);
    List<StudentResponseDTO> findStudentByName(String studentName);
    List<StudentResponseDTO> findAllDetails(Map<String, String> params);
}
