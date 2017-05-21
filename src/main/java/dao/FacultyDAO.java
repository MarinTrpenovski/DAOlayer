package dao;

import dao.generic.GenericDAO;
import entity.Faculty;
import entity.Student;
import exception.MyException;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by marin.trpenovski on 5/9/2017.
 */
public interface FacultyDAO extends GenericDAO<Faculty, Long> {

    List<Student> getAllStudentForFaculty(Long id) throws MyException;

    List<Faculty> getOrderFaculties() throws MyException;

    Faculty getFacultyWithHighestAverageGrade() throws MyException;

    Faculty getFacultyWithLowerAverageGrade() throws MyException;
}
