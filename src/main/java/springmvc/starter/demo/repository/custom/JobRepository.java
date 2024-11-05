package springmvc.starter.demo.repository.custom;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import springmvc.starter.demo.entity.JobEntity;

@Repository
public interface JobRepository extends JpaRepository<JobEntity, Long> {
    JobEntity findByStudentId(Long id);
}
