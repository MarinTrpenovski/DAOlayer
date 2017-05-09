package dao.impl;

import dao.FacultyDAO;
import dao.generic.GenericDAO;
import entity.Faculty;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by marin.trpenovski on 5/9/2017.
 */
public class FacultyDAOImpl implements FacultyDAO {

    @Override
    public List<Faculty> getAll() throws SQLException {
        return null;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public void update(Faculty faculty) {

    }

    @Override
    public void create(Faculty faculty) {

    }

    @Override
    public Object getOne(Long id) {
        return null;
    }
}
