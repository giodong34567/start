package springmvc.starter.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import springmvc.starter.demo.entity.StudentEntity;
import springmvc.starter.demo.repository.custom.StudentRepositoryCustom;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<StudentEntity, Long>, StudentRepositoryCustom {
    List<StudentEntity> findALlByFullnameContaining(String name);
}
