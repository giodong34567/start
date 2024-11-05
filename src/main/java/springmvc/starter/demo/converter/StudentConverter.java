package springmvc.starter.demo.converter;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import springmvc.starter.demo.dto.request.StudentDTO;
import springmvc.starter.demo.dto.response.StudentResponseDTO;
import springmvc.starter.demo.entity.JobEntity;
import springmvc.starter.demo.entity.MajorEntity;
import springmvc.starter.demo.entity.StudentEntity;
import springmvc.starter.demo.repository.MajorRepository;
import springmvc.starter.demo.repository.custom.JobRepository;

@Component
public class StudentConverter {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private MajorRepository majorRepository;

    public StudentResponseDTO convertToStudentDTO(StudentEntity studentEntity) {
        StudentResponseDTO studentResponseDTO = modelMapper.map(studentEntity, StudentResponseDTO.class);
        JobEntity jobEntity = jobRepository.findByStudentId(studentEntity.getId());
        if(jobEntity != null){
            studentResponseDTO.setCompanyName(jobEntity.getCompanyName());
        }
        return studentResponseDTO;
    }

    public StudentEntity convertToStudentEntity(StudentDTO studentDTO) {
        return modelMapper.map(studentDTO, StudentEntity.class);
    }
}
