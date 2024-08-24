package springmvc.starter.demo.servicre;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springmvc.starter.demo.dto.StudentDTO;
import springmvc.starter.demo.model.Student;
import springmvc.starter.demo.repository.StudentRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StudentService implements CRUD<StudentDTO, Long> {

    @Autowired
    private StudentRepository studentRepository;

    @Override
    public List<StudentDTO> findAll() {
        return studentRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<StudentDTO> findById(Long id) {
        return studentRepository.findById(id).map(this::convertToDTO);
    }

    @Override
    public StudentDTO save(StudentDTO studentDTO) {
        Student student = convertToEntity(studentDTO);
        return convertToDTO(studentRepository.save(student));
    }

    @Override
    public StudentDTO update(StudentDTO studentDTO) {
        Student student = convertToEntity(studentDTO);
        return convertToDTO(studentRepository.save(student));
    }

    @Override
    public void deleteById(Long id) {
        studentRepository.deleteById(id);
    }

    private StudentDTO convertToDTO(Student student) {
        return new StudentDTO(student.getId(), student.getName(), student.getEmail(), student.getAge());
    }

    private Student convertToEntity(StudentDTO studentDTO) {
        return new Student(studentDTO.getId(), studentDTO.getName(), studentDTO.getEmail(), studentDTO.getAge());
    }
}
