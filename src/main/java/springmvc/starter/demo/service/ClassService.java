package springmvc.starter.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springmvc.starter.demo.dto.ClassDTO;
import springmvc.starter.demo.exception.ConflictException;
import springmvc.starter.demo.model.Class;
import springmvc.starter.demo.repository.ClassRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service layer for handling business logic related to Class entities.
 */
@Service
public class ClassService implements CRUD<Class, Long, ClassDTO> {

    @Autowired
    private ClassRepository classRepository;

    /**
     * Converts a Class entity to a ClassDTO.
     *
     * @param classEntity The Class entity to convert.
     * @return The corresponding ClassDTO.
     */
    private ClassDTO convertToDto(Class classEntity) {
        return new ClassDTO(classEntity.getId(), classEntity.getName(), classEntity.getDescription());
    }

    /**
     * Retrieves all classes from the repository and maps them to DTOs.
     *
     * @return A list of ClassDTO objects.
     */
    public List<ClassDTO> findAll() {
        return classRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    /**
     * Finds a class by its ID and maps it to a DTO.
     *
     * @param id The ID of the class to find.
     * @return An Optional containing the found ClassDTO or empty if not found.
     */
    public Optional<ClassDTO> findById(Long id) {
        return classRepository.findById(id)
                .map(this::convertToDto);
    }

    /**
     * Saves a new class entity to the repository.
     *
     * @param classDTO The ClassDTO containing the data to save.
     */
    public Class save(ClassDTO classDTO) {
        if (checkExitsByName(classDTO.getName())) {
            throw new ConflictException("Class name already exists");
        }
        Class classEntity = new Class(classDTO.getId(), classDTO.getName(), classDTO.getDescription(), null);


        return classRepository.save(classEntity);
    }

    /**
     * Updates an existing class entity in the repository.
     *
     * @param classDTO The ClassDTO containing the updated data.
     */
    public Class update(ClassDTO classDTO) {
        Class classFindByID = classRepository.findById(classDTO.getId()).orElseThrow();

        if (!classFindByID.getName().equals(classDTO.getName()) && checkExitsByName(classDTO.getName())) {
            throw new ConflictException("Class name already exists");
        }

        Class classEntity = new Class(classDTO.getId(), classDTO.getName(), classDTO.getDescription(), null);


        return classRepository.save(classEntity);
    }

    /**
     * Deletes a class entity from the repository by its ID.
     *
     * @param id The ID of the class to delete.
     */
    public void deleteById(Long id) {
        classRepository.deleteById(id);
    }

    /**
     * Check if a class with the given name already exists in the repository.
     *
     * @param name The name to check.
     */
    boolean checkExitsByName(String name) {
        return this.classRepository.existsByName(name);
    }
}