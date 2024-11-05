package springmvc.starter.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import springmvc.starter.demo.converter.StudentConverter;
import springmvc.starter.demo.dto.request.StudentDTO;
import springmvc.starter.demo.dto.response.StudentResponseDTO;
import springmvc.starter.demo.entity.GraduationEntity;
import springmvc.starter.demo.entity.StudentEntity;
import springmvc.starter.demo.repository.GraduationRepository;
import springmvc.starter.demo.repository.MajorRepository;
import springmvc.starter.demo.repository.SchoolRepository;
import springmvc.starter.demo.repository.StudentRepository;
import springmvc.starter.demo.service.StudentService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Primary
@Transactional
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private StudentConverter studentConverter;

    @Autowired
    private GraduationRepository graduationRepository;

    @Autowired
    private MajorRepository majorRepository;

    @Autowired
    private SchoolRepository schoolRepository;

    @Override
    public List<StudentResponseDTO> findAll(){
        List<StudentEntity> studentEntities = studentRepository.findAll();
        List<StudentResponseDTO> studentDTOS = new ArrayList<>();
        for(StudentEntity studentEntity : studentEntities){
            StudentResponseDTO studentResponseDTO = studentConverter.convertToStudentDTO(studentEntity);
            studentDTOS.add(studentResponseDTO);
        }
        return studentDTOS;
    }

    @Override
    public void saveStudent(StudentDTO studentDTO) {
        StudentEntity studentEntity = studentConverter.convertToStudentEntity(studentDTO);
        studentRepository.save(studentEntity);
        GraduationEntity graduationEntity = new GraduationEntity();
        graduationEntity.setStudent(studentEntity);
        graduationEntity.setGraduationDate(studentDTO.getGraduationDate());
        graduationEntity.setMajor(majorRepository.findById(studentDTO.getMajorId()).get());
        graduationEntity.setSchool(schoolRepository.findById(studentDTO.getSchoolId()).get());
        graduationRepository.save(graduationEntity);
    }

    @Override
    public List<StudentResponseDTO> findStudentByName(String studentName) {
        List<StudentResponseDTO> studentDTOS = new ArrayList<>();
        List<StudentEntity> studentEntities = studentRepository.findALlByFullnameContaining(studentName);
        studentDTOS = studentEntities.stream().map(item -> studentConverter.convertToStudentDTO(item)).collect(Collectors.toList());
        return studentDTOS;
    }

    @Override
    public List<StudentResponseDTO> findAllDetails(Map<String, String> params) {
        List<StudentResponseDTO> studentResponseDTOS = new ArrayList<>();
        List<StudentEntity> studentEntities = studentRepository.findAllDetails(params);
        for(StudentEntity studentEntity : studentEntities){
            StudentResponseDTO studentResponseDTO = studentConverter.convertToStudentDTO(studentEntity);
            studentResponseDTOS.add(studentResponseDTO);
        }
        return studentResponseDTOS;
    }
}
