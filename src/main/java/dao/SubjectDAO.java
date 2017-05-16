package dao;

import dao.generic.GenericDAO;
import entity.Subject;

import java.util.List;

/**
 * Created by marin.trpenovski on 5/9/2017.
 */
public interface SubjectDAO extends GenericDAO<Subject, Long>{

    Subject getSubjectWithHighestCredits();

    Subject getSubjectWithLowerCredits();

    List<Subject> getSubjectsForSemester(Long semester);

    List<Subject> getNumberOfSubjectWithSpecificCredits(Long numberOfCredits);
}
