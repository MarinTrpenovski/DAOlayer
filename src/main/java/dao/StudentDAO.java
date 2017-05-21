package dao;

import dao.generic.GenericDAO;
import entity.Student;
import exception.MyException;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by marin.trpenovski on 4/27/2017.
 */
public interface StudentDAO extends GenericDAO<Student, Long> {

    Double getAverageGradeForStudent(Long id) throws MyException;

    Student getStudentWithHighestGrade() throws MyException;

    Student getStudentWithMostCredits() throws MyException;

    Student getStudentWithLeastCredits() throws MyException;

    List<Student> getAllStudentPerFaculty(Long facultyId) throws MyException;

    List<Student> getAllStudentPerUniversity(Long universityId) throws MyException;

}
