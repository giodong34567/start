package springmvc.starter.demo.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import springmvc.starter.demo.dto.ClassDTO;
import springmvc.starter.demo.dto.MarkDetailsDTO;
import springmvc.starter.demo.dto.StudentDTO;
import springmvc.starter.demo.dto.StudentDetailsDTO;
import springmvc.starter.demo.exception.NotFoundException;
import springmvc.starter.demo.exception.ResourceNotFoundException;
import springmvc.starter.demo.model.ClassEntity;
import springmvc.starter.demo.model.Mark;
import springmvc.starter.demo.model.Student;
import springmvc.starter.demo.model.Subject;
import springmvc.starter.demo.repository.MarkRepository;
import springmvc.starter.demo.repository.StudentRepository;
import springmvc.starter.demo.repository.SubjectRepository;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service layer for handling business logic related to Student entities.
 */
@Service
public class StudentService implements CRUD<Student, Long, StudentDTO> {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private ClassService classService;

    @Autowired
    private MarkRepository markRepository;

    @Autowired
    private SubjectRepository subjectRepository;

    /**
     * Retrieves all students from the repository and maps them to DTOs.
     *
     * @return A list of StudentDTO objects.
     */
    public List<StudentDTO> findAll() {
        return studentRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    // Find by name
    public List<StudentDTO> findAllByName(String name){
        return studentRepository.findAllByNameContaining(name).stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    //Find by class ID

    public List<StudentDTO> findAllByClassID(Long id){
        return studentRepository.findAllByClassId(id).stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    /**
     * Converts a Student entity to a StudentDTO.
     *
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
                        student.getClassEntity().getId(),
                        student.getClassEntity().getName(),
                        student.getClassEntity().getDescription()
                )
        );
    }

    /**
     * Finds a student by their ID and maps it to a DTO.
     *
     * @param id The ID of the student to find.
     * @return An Optional containing the found StudentDTO or empty if not found.
     */
    public Optional<StudentDTO> findById(Long id) {
        return studentRepository.findById(id)
                .map(this::convertToDTO);
    }

    /**
     * Saves a new student entity to the repository.
     *
     * @param studentDTO The StudentDTO containing the data to save.
     */
    public Student save(StudentDTO studentDTO) {
        // Use studentDTO.getStudentClass().getId() to retrieve the class ID
        ClassDTO studentClass = classService.findById(studentDTO.getStudentClass().getId()).orElse(null);
        ClassEntity classEntity = new ClassEntity();

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
                classEntity,
                null
        );

        try {
            return studentRepository.save(student);
        } catch (Exception e) {
            throw new NotFoundException(e.getMessage());
        }
    }

    /**
     * Updates an existing student entity in the repository.
     *
     * @param studentDTO The StudentDTO containing the updated data.
     */
    public Student update(StudentDTO studentDTO) {
        Student student = studentRepository.findById(studentDTO.getId()).orElse(null);
        ClassEntity classEntity = new ClassEntity();
        if (student != null) {
            if (!Objects.equals(student.getClassEntity().getId(), studentDTO.getStudentClass().getId())) {
                ClassDTO studentClass = classService.findById(studentDTO.getStudentClass().getId()).orElse(null);
                if (studentClass != null) {
                    BeanUtils.copyProperties(studentClass, classEntity);
                } else {
                    throw new NotFoundException("Class not found");
                }
            } else {
                classEntity = student.getClassEntity();
            }
            student.setName(studentDTO.getName());
            student.setEmail(studentDTO.getEmail());
            student.setAge(studentDTO.getAge());
            student.setClassEntity(classEntity);
            return studentRepository.save(student);
        } else {
            throw new NotFoundException("Student not found");
        }
    }

    /**
     * Deletes a student entity from the repository by its ID.
     *
     * @param id The ID of the student to delete.
     */

    @Transactional
    public void deleteById(Long id) {
        Student student = studentRepository.findById(id).get();
        markRepository.deleteAllByStudent(student);
        studentRepository.deleteById(id);
    }
    public StudentDetailsDTO getStudentDetails(Long studentId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found"));


        List<Mark> marks = markRepository.findAllByStudentId(studentId);

        StudentDetailsDTO studentDetailsDTO = new StudentDetailsDTO();
        studentDetailsDTO.setStudentName(student.getName());
        studentDetailsDTO.setEmail(student.getEmail());
        studentDetailsDTO.setAge(student.getAge());

        List<MarkDetailsDTO> markDetails = marks.stream().map(mark -> {
            Subject subject = mark.getSubject();

            String subjectName = (subject != null) ? subject.getName() : "Unknown Subject";

            return new MarkDetailsDTO(
                    mark.getMark(),
                    mark.getMarkType(),
                    subjectName
            );
        }).collect(Collectors.toList());

        studentDetailsDTO.setMarkDetails(markDetails);
        return studentDetailsDTO;
    }
}