package dao;

import dao.generic.GenericDAO;
import entity.Faculty;
import entity.Student;

import java.util.List;

/**
 * Created by marin.trpenovski on 5/9/2017.
 */
public interface FacultyDAO extends GenericDAO<Faculty, Long> {

    List<Student> getAllStudentForFaculty();

    List<Faculty> getOrderFaculties();

    Faculty getFacultyWithHighestAverageGrade();

    Faculty getFacultyWithLowerAverageGrade();
}
