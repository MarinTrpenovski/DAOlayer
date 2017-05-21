package dao.impl;

import DBCP_DB_Pooling.DataSource;
import connections.DefaultConnectionFactory;
import dao.SubjectDAO;
import dao.generic.GenericDAO;
import entity.Student;
import entity.Subject;
import exception.MyException;

import javax.xml.crypto.Data;
import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by marin.trpenovski on 5/9/2017.
 */
public class SubjectDAOImpl implements SubjectDAO {

    @Override
    public List<Subject> getAll() throws MyException {

        List<Subject> subjects = null;
        Connection conn = null;
        try {
            conn = DefaultConnectionFactory.getInstance().getConnection();
            PreparedStatement pr  = conn.prepareStatement("select * from subject");
            subjects =  new ArrayList<>();
            ResultSet rs = pr.executeQuery();
            while (rs.next()){
                Long id = rs.getLong("id");
                String name = rs.getString("name");
                Long credits = rs.getLong("credits");
                Long semester = rs.getLong("semester");
                Subject student =  new Subject(id, name, credits, semester);
                subjects.add(student);
            }
            return subjects;
        } catch (Exception ex){
            throw new MyException("");
        } finally {
            DataSource.getInstance().closeConnection(conn);
        }
    }

    @Override
    public void delete(Long id) throws MyException {
        Connection conn = null;
        try {
            conn = DefaultConnectionFactory.getInstance().getConnection();
            PreparedStatement pr  = conn.prepareStatement("delete from subject where subject.id = ?");
            pr.setLong(1, id);
            int number = pr.executeUpdate();
        } catch(Exception e){
            throw new MyException("");
        } finally {
            DataSource.getInstance().closeConnection(conn);
        }
    }

    @Override
    public void update(Subject subject) throws MyException {
        Connection conn = null;
        try {
            conn = DefaultConnectionFactory.getInstance().getConnection();
            PreparedStatement pr  = conn.prepareStatement("update subject set name = ? , credits = ?, semester = ? where subject.id = ?");
            pr.setString(1, subject.getName());
            pr.setLong(2, subject.getCredits());
            pr.setLong(3, subject.getSemester());
            pr.setLong(4, subject.getId());
            int number = pr.executeUpdate();
        } catch(Exception e){
            throw new MyException("");
        } finally {
            DataSource.getInstance().closeConnection(conn);
        }
    }

    @Override
    public void create(Subject subject) throws MyException {
        Connection conn = null;
        try {
            conn = DefaultConnectionFactory.getInstance().getConnection();
            PreparedStatement pr  = conn.prepareStatement("insert into subject (name, credits, semester) VALUES ( ?, ?, ?)");
            pr.setString(1, subject.getName());
            pr.setLong(2, subject.getCredits());
            pr.setLong(3, subject.getSemester());
            int number = pr.executeUpdate();
        } catch(Exception e){
            throw new MyException("");
        } finally {
            DataSource.getInstance().closeConnection(conn);
        }
    }

    @Override
    public Subject getOne(Long id) throws MyException {

        Subject subject = null;
        Connection conn = null;
        try {
            conn = DefaultConnectionFactory.getInstance().getConnection();
            PreparedStatement pr  = conn.prepareStatement("select * from subject as st where st.id = ?");
            pr.setString(1, id.toString());
            ResultSet rs = pr.executeQuery();
            rs.next();
            subject = new Subject(rs.getLong("id"), rs.getString("name"), rs.getLong("credits"), rs.getLong("semester"));
        } catch(Exception e){
            throw new MyException("");
        } finally {
            DataSource.getInstance().closeConnection(conn);
        }
        return subject;
    }

    @Override
    public Subject getSubjectWithHighestCredits() throws MyException {
        Connection conn = null;
        Subject subject = null;
        try {
            conn = DataSource.getInstance().getConnection();
            String sql = "SELECT * , max(credits) as MaxCredits FROM exercise.subject s\n" +
                    "group by id\n" +
                    "order by MaxCredits DESC\n" +
                    "limit 1;";
            PreparedStatement pr = conn.prepareStatement(sql);
            ResultSet rs = pr.executeQuery();
            rs.next();
            Long id = rs.getLong("id");
            Long semester = rs.getLong("semester");
            String name = rs.getString("name");
            Long credits = rs.getLong("credits");
            subject = new Subject(id, name, semester, credits);
            return subject;
        } catch (Exception e) {
            throw new MyException("");
        } finally {
            DataSource.getInstance().closeConnection(conn);
        }
    }

    @Override
    public Subject getSubjectWithLowerCredits() throws MyException{
        Connection conn = null;
        Subject subject = null;
        try {
            conn = DataSource.getInstance().getConnection();
            String sql = "SELECT * , min(credits) as MaxCredits FROM exercise.subject s\n" +
                    "group by id\n" +
                    "order by MaxCredits ASC\n" +
                    "limit 1;";
            PreparedStatement pr = conn.prepareStatement(sql);
            ResultSet rs = pr.executeQuery();
            rs.next();
            Long id = rs.getLong("id");
            Long semester = rs.getLong("semester");
            String name = rs.getString("name");
            Long credits = rs.getLong("credits");
            subject = new Subject(id, name, semester, credits);
            return subject;
        } catch (Exception e) {
            throw new MyException("");
        } finally {
            DataSource.getInstance().closeConnection(conn);
        }
    }

    @Override
    public List<Subject> getSubjectsForSemesterForFaculty(Long semester, Long facultyId) throws MyException {
        Connection conn = null;
        List<Subject> subjectList = null;
        try {
            conn = DataSource.getInstance().getConnection();
            String sql = "SELECT s.* FROM exercise.subject s\n" +
                    "inner join student_subject ss on ss.subjectId = s.id\n" +
                    "inner join student st on st.id = ss.studentId\n" +
                    "inner join faculty f on f.id = st.facultyId\n" +
                    "where f.id = ?  and s.semester = ?;";
            PreparedStatement pr = conn.prepareStatement(sql);
            pr.setLong(1, facultyId);
            pr.setLong(2, semester);
            ResultSet rs = pr.executeQuery();
            subjectList = new ArrayList<>();
            while (rs.next()) {
                Subject subject = new Subject(rs.getLong("id"), rs.getString("name"), rs.getLong("credits"), rs.getLong("semester"));
                subjectList.add(subject);
            }
            return subjectList;
        } catch (Exception e) {
            throw new MyException("");
        } finally {
            DataSource.getInstance().closeConnection(conn);
        }
    }

    @Override
    public Long getNumberOfSubjectWithSpecificCredits(Long numberOfCredits) throws MyException {
        Connection conn = null;
        try {
            conn = DataSource.getInstance().getConnection();
            String sql = "SELECT count(id) as total FROM exercise.subject s\n" +
                    "where s.credits = ?";
            PreparedStatement pr = conn.prepareStatement(sql);
            pr.setLong(1, numberOfCredits);
            ResultSet rs = pr.executeQuery();
            rs.next();
            Long totalNumberOfSubject = rs.getLong("total");
            System.out.println("Total number of subject with " + numberOfCredits + " is " + totalNumberOfSubject);
            return totalNumberOfSubject;
        } catch (Exception e) {
            throw new MyException("");
        } finally {
            DataSource.getInstance().closeConnection(conn);
        }
    }

    @Override
    public Long totalCreditsPerSemesterPerFaculty(Long semesterId, Long facultyId) throws MyException {
        Connection conn = null;
        try {
            conn = DataSource.getInstance().getConnection();
            String sql = "SELECT sum(credits) as TotalCredits from exercise.subject su\n" +
                    "inner join student_subject ss on ss.subjectId = su.id\n" +
                    "inner join student st on st.id = ss.studentId\n" +
                    "inner join faculty f on f.id = st.facultyId\n" +
                    "where f.id = ? and su.semester = ?";
            PreparedStatement pr = conn.prepareStatement(sql);
            pr.setLong(1, facultyId);
            pr.setLong(2, semesterId);
            ResultSet rs = pr.executeQuery();
            Long totalCreditsPerSemester = rs.getLong("TotalCredits");
            System.out.println("Number of credits are " +  totalCreditsPerSemester);
            return totalCreditsPerSemester;
        } catch (Exception e) {
            throw new MyException("");
        } finally {
            DataSource.getInstance().closeConnection(conn);
        }
    }
}
