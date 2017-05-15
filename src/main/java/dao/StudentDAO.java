package dao;

import dao.generic.GenericDAO;
import entity.Student;

/**
 * Created by marin.trpenovski on 4/27/2017.
 */
public interface StudentDAO extends GenericDAO<Student, Long> {

    Integer getAverageGrade();

    Student getStudentWithHighestGrade();


}
