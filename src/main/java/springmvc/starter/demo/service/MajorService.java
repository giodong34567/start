package springmvc.starter.demo.service;

import springmvc.starter.demo.dto.response.MajorResponseDTO;
import springmvc.starter.demo.entity.MajorEntity;

import java.util.List;

public interface MajorService {
    List<MajorResponseDTO> findAll();
}
