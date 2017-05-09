package dao.impl;

import dao.UniversityDAO;
import dao.generic.GenericDAO;
import entity.University;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by marin.trpenovski on 5/9/2017.
 */
public class UniversityDAOImpl implements UniversityDAO {

    @Override
    public List<University> getAll() throws SQLException {
        return null;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public void update(University o) {

    }

    @Override
    public void create(University o) {

    }

    @Override
    public Object getOne(Long id) {
        return null;
    }
}
