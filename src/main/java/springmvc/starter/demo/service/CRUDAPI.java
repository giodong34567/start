package springmvc.starter.demo.service;

import springmvc.starter.demo.service.CRUD;
import java.util.List;

public interface CRUDAPI<T, ID, DTO> extends CRUD<T, ID, DTO> {

    List<T> findAllByName(String name);
}
