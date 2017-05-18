package dao;

import dao.generic.GenericDAO;
import entity.Subject;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by marin.trpenovski on 5/9/2017.
 */
public interface SubjectDAO extends GenericDAO<Subject, Long>{

    Subject getSubjectWithHighestCredits() throws PropertyVetoException, SQLException, IOException;

    Subject getSubjectWithLowerCredits() throws PropertyVetoException, SQLException, IOException;

    List<Subject> getSubjectsForSemesterForFaculty(Long semester, Long facultyId) throws PropertyVetoException, SQLException, IOException;

    Long getNumberOfSubjectWithSpecificCredits(Long numberOfCredits) throws PropertyVetoException, SQLException, IOException;

    Long totalCreditsPerSemesterPerFaculty(Long semesterId, Long facultyId) throws PropertyVetoException, SQLException, IOException;
}
