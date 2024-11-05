package springmvc.starter.demo.service;


import springmvc.starter.demo.dto.request.StudentDTO;

public interface GraduationService {
    void saveGraduation(StudentDTO studentDTO);
}
