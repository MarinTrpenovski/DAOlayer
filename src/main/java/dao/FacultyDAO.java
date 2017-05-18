package dao;

import dao.generic.GenericDAO;
import entity.Faculty;
import entity.Student;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by marin.trpenovski on 5/9/2017.
 */
public interface FacultyDAO extends GenericDAO<Faculty, Long> {

    List<Student> getAllStudentForFaculty(Long id) throws PropertyVetoException, SQLException, IOException;

    List<Faculty> getOrderFaculties() throws PropertyVetoException, SQLException, IOException;

    Faculty getFacultyWithHighestAverageGrade() throws PropertyVetoException, SQLException, IOException;

    Faculty getFacultyWithLowerAverageGrade() throws PropertyVetoException, SQLException, IOException;
}
