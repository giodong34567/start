package springmvc.starter.demo.service;


import springmvc.starter.demo.dto.response.SchoolResponseDTO;

import java.util.List;

public interface SchoolService {
    List<SchoolResponseDTO> findAll();
}
