package dao;

import dao.generic.GenericDAO;
import entity.Faculty;
import entity.University;

import java.util.List;

/**
 * Created by marin.trpenovski on 5/9/2017.
 */
public interface UniversityDAO extends GenericDAO<University, Long> {

    List<Faculty> getFacultiesForUniversity(Long universityId);

    Integer getNumberOfStudentsForUniversity(Long universityId);
}
