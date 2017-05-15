package dao.generic;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by marin.trpenovski on 4/27/2017.
 */
public interface GenericDAO<T, ID extends Serializable>{

    List<T> getAll() throws PropertyVetoException, SQLException, IOException;

    <T> T getOne(ID id) throws PropertyVetoException, SQLException, IOException;

    void delete(ID id) throws PropertyVetoException, SQLException, IOException;

    void update(T t) throws PropertyVetoException, SQLException, IOException;

    void create (T t) throws PropertyVetoException, SQLException, IOException;

}
