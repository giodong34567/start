package springmvc.starter.demo.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springmvc.starter.demo.dto.ClassDTO;
import springmvc.starter.demo.dto.StudentDTO;
import springmvc.starter.demo.exception.NotFoundException;
import springmvc.starter.demo.model.Class;
import springmvc.starter.demo.model.Student;
import springmvc.starter.demo.repository.StudentRepository;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service layer for handling business logic related to Student entities.
 */
@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private ClassService classService;

    /**
     * Retrieves all students from the repository and maps them to DTOs.
     * @return A list of StudentDTO objects.
     */
    public List<StudentDTO> findAll() {
        return studentRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    /**
     * Converts a Student entity to a StudentDTO.
     * @param student The Student entity to convert.
     * @return The converted StudentDTO.
     */
    public StudentDTO convertToDTO(Student student) {
        return new StudentDTO(
                student.getId(),
                student.getName(),
                student.getEmail(),
                student.getAge(),
                new ClassDTO(
                        student.getStudentClass().getId(),
                        student.getStudentClass().getName(),
                        student.getStudentClass().getDescription()
                )
        );
    }

    /**
     * Finds a student by their ID and maps it to a DTO.
     * @param id The ID of the student to find.
     * @return An Optional containing the found StudentDTO or empty if not found.
     */
    public Optional<StudentDTO> findById(Long id) {
        return studentRepository.findById(id)
                .map(this::convertToDTO);
    }

    /**
     * Saves a new student entity to the repository.
     * @param studentDTO The StudentDTO containing the data to save.
     */
    public void save(StudentDTO studentDTO) {
        // Use studentDTO.getStudentClass().getId() to retrieve the class ID
        ClassDTO studentClass = classService.findById(studentDTO.getStudentClass().getId()).orElse(null);
        Class classEntity = new Class();

        if (studentClass != null) {
            BeanUtils.copyProperties(studentClass, classEntity);
        } else {
            throw new NotFoundException("Class not found");
        }

        Student student = new Student(
                studentDTO.getId(), // This is the student's ID, which may be null for new students
                studentDTO.getName(),
                studentDTO.getEmail(),
                studentDTO.getAge(),
                classEntity
        );

        try {
            studentRepository.save(student);
        } catch (Exception e) {
            throw new NotFoundException(e.getMessage());
        }
    }

    /**
     * Updates an existing student entity in the repository.
     * @param studentDTO The StudentDTO containing the updated data.
     * @param id The ID of the student to update.
     */
    public void update(StudentDTO studentDTO, Long id) {
        Student student = studentRepository.findById(id).orElse(null);
        Class classEntity = new Class();
        if (student != null) {
            if (!Objects.equals(student.getStudentClass().getId(), studentDTO.getStudentClass().getId())) {
                ClassDTO studentClass = classService.findById(studentDTO.getStudentClass().getId()).orElse(null);
                if (studentClass != null) {
                    BeanUtils.copyProperties(studentClass, classEntity);
                } else {
                    throw new NotFoundException("Class not found");
                }
            } else {
                classEntity = student.getStudentClass();
            }
            student.setName(studentDTO.getName());
            student.setEmail(studentDTO.getEmail());
            student.setAge(studentDTO.getAge());
            student.setStudentClass(classEntity);
            studentRepository.save(student);
        } else {
           throw new NotFoundException("Student not found");
        }
    }

    /**
     * Deletes a student entity from the repository by its ID.
     * @param id The ID of the student to delete.
     */
    public void deleteById(Long id) {
        studentRepository.deleteById(id);
    }
}