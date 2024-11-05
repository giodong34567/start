package springmvc.starter.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import springmvc.starter.demo.dto.request.StudentDTO;
import springmvc.starter.demo.entity.GraduationEntity;
import springmvc.starter.demo.repository.GraduationRepository;
import springmvc.starter.demo.repository.MajorRepository;
import springmvc.starter.demo.repository.SchoolRepository;
import springmvc.starter.demo.repository.StudentRepository;
import springmvc.starter.demo.service.GraduationService;
import springmvc.starter.demo.service.SchoolService;

@Service
@Transactional
public class GraduationServiceImpl implements GraduationService {

    @Autowired
    private GraduationRepository graduationRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private MajorRepository majorRepository;

    @Autowired
    private SchoolRepository schoolRepository;

    @Override
    public void saveGraduation(StudentDTO studentDTO) {
        System.out.println(1);
    }
}
