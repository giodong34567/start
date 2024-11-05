package springmvc.starter.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import springmvc.starter.demo.entity.GraduationEntity;

@Repository
public interface GraduationRepository extends JpaRepository<GraduationEntity, Long> {
}
