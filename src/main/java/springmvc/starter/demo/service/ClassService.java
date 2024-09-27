package springmvc.starter.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springmvc.starter.demo.converter.ClassConverter;
import springmvc.starter.demo.dto.ClassDTO;
import springmvc.starter.demo.exception.ConflictException;
import springmvc.starter.demo.model.ClassEntity;
import springmvc.starter.demo.repository.ClassRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service layer for handling business logic related to Class entities.
 */
@Service
public class ClassService implements CRUD<ClassEntity, Long, ClassDTO> {

    @Autowired
    private ClassRepository classRepository;

    @Autowired
    private ClassConverter classConverter;
    /**
     * Converts a Class entity to a ClassDTO.
     *
     * @param classEntity The Class entity to convert.
     * @return The corresponding ClassDTO.
     */

    /**
     * Retrieves all classes from the repository and maps them to DTOs.
     *
     * @return A list of ClassDTO objects.
     */

    public List<ClassDTO> findAll() {
        return classRepository.findAll().stream()
                .map(classEntity -> classConverter.convertToDto(classEntity))
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
                .map(classEntity -> classConverter.convertToDto(classEntity));
    }

    /**
     * Saves a new class entity to the repository.
     *
     * @param classDTO The ClassDTO containing the data to save.
     */
    public ClassEntity save(ClassDTO classDTO) {
        if (checkExitsByName(classDTO.getName())) {
            throw new ConflictException("Class name already exists");
        }
        ClassEntity classEntity = new ClassEntity(classDTO.getId(), classDTO.getName(), classDTO.getDescription(), null);

        return classRepository.save(classEntity);
    }

    /**
     * Updates an existing class entity in the repository.
     *
     * @param classDTO The ClassDTO containing the updated data.
     */
    public ClassEntity update(ClassDTO classDTO) {
        ClassEntity classEntityFindByID = classRepository.findById(classDTO.getId()).orElseThrow();

        if (!classEntityFindByID.getName().equals(classDTO.getName()) && checkExitsByName(classDTO.getName())) {
            throw new ConflictException("Class name already exists");
        }

        ClassEntity classEntity = new ClassEntity(classDTO.getId(), classDTO.getName(), classDTO.getDescription(), null);

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