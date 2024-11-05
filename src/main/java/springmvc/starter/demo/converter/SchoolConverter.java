package springmvc.starter.demo.converter;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import springmvc.starter.demo.dto.response.SchoolResponseDTO;
import springmvc.starter.demo.entity.SchoolEntity;

@Component
public class SchoolConverter {
    @Autowired
    ModelMapper modelMapper;

    public SchoolResponseDTO convertToSchoolResponseDTO(SchoolEntity schoolEntity) {
        return modelMapper.map(schoolEntity, SchoolResponseDTO.class);
    }
}
