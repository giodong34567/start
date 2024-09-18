package springmvc.starter.demo.service;

import java.util.List;
import java.util.Optional;

public interface CRUD<T, ID, DTO> {

    List<DTO> findAll();

    Optional<DTO> findById(ID id);

    T save(DTO createDto);

    T update(DTO updateDto);

    void deleteById(ID id);
}