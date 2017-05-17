package dao;

import dao.generic.GenericDAO;
import entity.Student;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by marin.trpenovski on 4/27/2017.
 */
public interface StudentDAO extends GenericDAO<Student, Long> {

    Double getAverageGradeForStudent(Long id) throws PropertyVetoException, SQLException, IOException;

    Student getStudentWithHighestGrade() throws PropertyVetoException, SQLException, IOException;

    Student getStudentWithMostCredits() throws PropertyVetoException, SQLException, IOException;

    Student getStudentWithLeastCredits() throws PropertyVetoException, SQLException, IOException;

}
