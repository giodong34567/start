package springmvc.starter.demo.converter;

import org.springframework.stereotype.Component;
import springmvc.starter.demo.dto.ClassDTO;
import springmvc.starter.demo.model.Class;

@Component
public class ClassConverter {
    public ClassDTO convertToDto(Class classEntity) {
        return new ClassDTO(classEntity.getId(), classEntity.getName(), classEntity.getDescription());
    }
}
