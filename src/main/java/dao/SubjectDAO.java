package dao;

import dao.generic.GenericDAO;
import entity.Subject;
import exception.MyException;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by marin.trpenovski on 5/9/2017.
 */
public interface SubjectDAO extends GenericDAO<Subject, Long>{

    Subject getSubjectWithHighestCredits() throws MyException;

    Subject getSubjectWithLowerCredits() throws MyException;

    List<Subject> getSubjectsForSemesterForFaculty(Long semester, Long facultyId) throws MyException;

    Long getNumberOfSubjectWithSpecificCredits(Long numberOfCredits) throws MyException;

    Long totalCreditsPerSemesterPerFaculty(Long semesterId, Long facultyId) throws MyException;
}
