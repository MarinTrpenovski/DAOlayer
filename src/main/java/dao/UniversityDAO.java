package dao;

import dao.generic.GenericDAO;
import entity.Faculty;
import entity.University;
import exception.MyException;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by marin.trpenovski on 5/9/2017.
 */
public interface UniversityDAO extends GenericDAO<University, Long> {

    List<Faculty> getFacultiesForUniversity(Long universityId) throws MyException;

    Integer getNumberOfStudentsForUniversity(Long universityId) throws MyException;
}
