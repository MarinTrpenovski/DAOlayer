package dao.generic;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by marin.trpenovski on 4/27/2017.
 */
public abstract class DAOFactory<T> {

    public abstract List<?> getAll() throws SQLException;

    public abstract  <T> T getOne(Long id);

    public abstract void delete(Long id);

    public abstract void update(T t);

    public abstract void create (T t);

}
