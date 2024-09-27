package springmvc.starter.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import springmvc.starter.demo.model.Subject;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, Long> {
}
