package springmvc.starter.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springmvc.starter.demo.converter.MajorConverter;
import springmvc.starter.demo.dto.response.MajorResponseDTO;
import springmvc.starter.demo.entity.MajorEntity;
import springmvc.starter.demo.repository.MajorRepository;
import springmvc.starter.demo.service.MajorService;

import java.util.ArrayList;
import java.util.List;

@Service
public class MajorServiceImpl implements MajorService {
    @Autowired
    private MajorRepository majorRepository;

    @Autowired
    private MajorConverter majorConverter;

    @Override
    public List<MajorResponseDTO> findAll() {
        List<MajorResponseDTO> majorResponseDTOS = new ArrayList<>();
        List<MajorEntity> majorEntities = majorRepository.findAll();
        for(MajorEntity majorEntity : majorEntities){
            MajorResponseDTO majorResponseDTO = majorConverter.convertToMajorResponseDTO(majorEntity);
            majorResponseDTOS.add(majorResponseDTO);
        }
        return majorResponseDTOS;
    }
}
