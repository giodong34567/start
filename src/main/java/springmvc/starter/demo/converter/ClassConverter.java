package springmvc.starter.demo.converter;

import org.springframework.stereotype.Component;
import springmvc.starter.demo.dto.ClassDTO;
import springmvc.starter.demo.model.ClassEntity;

@Component
public class ClassConverter {
    public ClassDTO convertToDto(ClassEntity classEntity) {
        return new ClassDTO(classEntity.getId(), classEntity.getName(), classEntity.getDescription());
    }
}
