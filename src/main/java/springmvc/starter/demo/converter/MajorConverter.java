package springmvc.starter.demo.converter;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import springmvc.starter.demo.dto.response.MajorResponseDTO;
import springmvc.starter.demo.entity.MajorEntity;

@Component
public class MajorConverter {

    @Autowired
    private ModelMapper modelMapper;
    public MajorResponseDTO convertToMajorResponseDTO(MajorEntity majorEntity) {
        MajorResponseDTO dto = modelMapper.map(majorEntity, MajorResponseDTO.class);
        return dto;
    }
}
