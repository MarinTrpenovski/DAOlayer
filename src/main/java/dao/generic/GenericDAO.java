package dao.generic;

import exception.MyException;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by marin.trpenovski on 4/27/2017.
 */
public interface GenericDAO<T, ID extends Serializable>{

    List<T> getAll() throws MyException;

    <T> T getOne(ID id) throws MyException;

    void delete(ID id) throws MyException;

    void update(T t) throws MyException;

    void create (T t) throws MyException;

}
