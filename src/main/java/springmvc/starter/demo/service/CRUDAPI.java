import springmvc.starter.demo.service.CRUD;
import java.util.List;

public interface CRUDAPI<T, ID> extends CRUD<T, ID> {

    List<T> findAllByName(String name);
}
