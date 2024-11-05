package springmvc.starter.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import springmvc.starter.demo.entity.JobEntity;
import springmvc.starter.demo.entity.MajorEntity;

@Repository
public interface MajorRepository extends JpaRepository<MajorEntity, Long> {
//    MajorEntity findAllByJob(JobEntity job);
}
