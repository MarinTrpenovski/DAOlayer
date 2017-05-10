package dao.generic;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by marin.trpenovski on 4/27/2017.
 */
public interface GenericDAO<T, ID extends Serializable>{

    List<T> getAll();

    <T> T getOne(ID id);

    void delete(ID id);

    void update(T t);

    void create (T t);

}
