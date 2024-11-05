package springmvc.starter.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springmvc.starter.demo.converter.SchoolConverter;
import springmvc.starter.demo.dto.response.SchoolResponseDTO;
import springmvc.starter.demo.entity.SchoolEntity;
import springmvc.starter.demo.repository.SchoolRepository;
import springmvc.starter.demo.service.SchoolService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SchoolServiceImpl implements SchoolService {

    @Autowired
    private SchoolRepository schoolRepository;

    @Autowired
    private SchoolConverter schoolConverter;

    @Override
    public List<SchoolResponseDTO> findAll() {
        List<SchoolEntity> schoolEntities = schoolRepository.findAll();
        List<SchoolResponseDTO> schoolResponseDTOs = schoolEntities.stream().map(x -> schoolConverter.convertToSchoolResponseDTO(x)).collect(Collectors.toList());
        return schoolResponseDTOs;
    }
}
