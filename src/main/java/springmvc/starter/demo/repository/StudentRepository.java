package springmvc.starter.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import springmvc.starter.demo.model.Student;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long>, JpaSpecificationExecutor<Student> {
    List<Student> findAllByNameContaining(String name);

    @Query(value = "SELECT s.* FROM student s INNER JOIN class c ON s.class_id = c.id WHERE c.id = :id", nativeQuery = true)
    List<Student> findAllByClassId(Long id);
}