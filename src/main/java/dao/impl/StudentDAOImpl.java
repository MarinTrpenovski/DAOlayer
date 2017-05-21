package dao.impl;

import DBCP_DB_Pooling.DataSource;
import com.mysql.cj.jdbc.exceptions.MySQLQueryInterruptedException;
import connections.DefaultConnectionFactory;
import dao.StudentDAO;
import entity.Student;
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
 * Created by marin.trpenovski on 4/27/2017.
 */
public class StudentDAOImpl implements StudentDAO{

    public List<Student> getAll() throws MyException {
        List<Student> studentList = null;
        Connection conn = null;
        try {
            conn = DataSource.getInstance().getConnection();
            PreparedStatement pr  = conn.prepareStatement("select * from student");
            studentList =  new ArrayList<>();
            ResultSet rs = pr.executeQuery();
            while (rs.next()){
                Long id = rs.getLong("id");
                String name = rs.getString("name");
                String surname = rs.getString("surname");
                Student student =  new Student(id, name, surname);
                studentList.add(student);
            }
            conn.close();
            return studentList;
        } catch (Exception ex){

        } finally {
            DataSource.getInstance().closeConnection(conn);
        }
        return studentList;

    }

    public Student getOne(Long id) throws MyException {

        Student student = null;
        Connection conn = null;
        try {
            conn = DataSource.getInstance().getConnection();
            PreparedStatement pr  = conn.prepareStatement("select * from student as st where st.id = ?");
            pr.setString(1, id.toString());
            ResultSet rs = pr.executeQuery();
            rs.next();
            student = new Student(rs.getLong("id"), rs.getString("name"), rs.getString("surname"));
            conn.close();

        } catch(Exception e){
            System.out.println("Error while fetching student " + e.getMessage());
        } finally {
            DataSource.getInstance().closeConnection(conn);
        }
        return student;
    }

    public void delete(Long id) throws MyException {
        Connection conn = null;
        try {
            conn = DataSource.getInstance().getConnection();
            PreparedStatement pr  = conn.prepareStatement("delete from student where student.id = ?");
            pr.setLong(1, id);
            int number = pr.executeUpdate();
            conn.close();

        } catch(Exception e){
            throw new MyException("");
        } finally {
            DataSource.getInstance().closeConnection(conn);
        }
    }

    public void update(Student student) throws MyException {
        Connection conn = null;
        try {
            conn = DataSource.getInstance().getConnection();
            PreparedStatement pr  = conn.prepareStatement("update student set name = ? , surname = ? where student.id = ?");
            pr.setString(1, student.getName());
            pr.setString(2, student.getSurname());
            pr.setLong(3, student.getId());
            int number = pr.executeUpdate();
            conn.close();

        } catch(Exception e){
            throw new MyException("");
        } finally {
            DataSource.getInstance().closeConnection(conn);
        }
    }

    public void create(Student student) throws MyException {
        Connection conn = null;
        try {
            conn = DataSource.getInstance().getConnection();
            PreparedStatement pr  = conn.prepareStatement("insert into student (name, surname) VALUES ( ?, ?)");
            pr.setString(1, student.getName());
            pr.setString(2, student.getSurname());
            int number = pr.executeUpdate();
            conn.close();

        } catch(Exception e){
            throw new MyException("");
        } finally {
            DataSource.getInstance().closeConnection(conn);
        }
    }

    @Override
    public Double getAverageGradeForStudent(Long id) throws MyException {
        Connection conn = null;
        try {
            conn = DataSource.getInstance().getConnection();
            PreparedStatement pr = conn.prepareStatement("select avg(grade) as averageGrade from student_subject where student_subject.studentId = ?");
            pr.setLong(1, id);
            ResultSet rs = pr.executeQuery();
            rs.next();
            Double averageGrade = rs.getDouble("averageGrade");
            return averageGrade;
        } catch (SQLException e) {
            throw new MyException("");
        } finally {
            DataSource.getInstance().closeConnection(conn);
        }
    }

    @Override
    public Student getStudentWithHighestGrade() throws MyException {
        Connection conn = null;
        try {
            conn =  DataSource.getInstance().getConnection();
            final String sql = "select * from student s where s.id = (select SS.studentId from student_subject SS inner join (select id, max(grade) as MaxGrade from student_subject group by grade) groupedSS on SS.id = groupedSS.MaxGrade)";
            PreparedStatement pr = conn.prepareStatement(sql);
            ResultSet rs = pr.executeQuery();
            rs.next();
            Long id = rs.getLong("id");
            String name =  rs.getString("name");
            String surname = rs.getString("surname");
            Student st = new Student(id, name, surname);
            return st;
        } catch (Exception e) {
            throw new MyException("");
        } finally {
            DataSource.getInstance().closeConnection(conn);
        }
    }

    @Override
    public Student getStudentWithMostCredits() throws MyException {
        Connection conn = null;
        try {
            conn = DataSource.getInstance().getConnection();
            String sql = "select st.*, sum(credits) as Total from exercise.student st\n" +
                    "\tjoin student_subject ss on ss.studentId = st.id\n" +
                    "    join subject su on su.id = ss.subjectId\n" +
                    "    group by studentId\n" +
                    "    order by Total DESC\n" +
                    "    limit 1";
            PreparedStatement pr = conn.prepareStatement(sql);
            ResultSet rs = pr.executeQuery();
            rs.next();
            Long id = rs.getLong("id");
            String name =  rs.getString("name");
            String surname = rs.getString("surname");
            Student st = new Student(id, name, surname);
            return st;
        }catch (Exception e) {
            throw new MyException("");
        }
        finally {
            DataSource.getInstance().closeConnection(conn);
        }
    }

    @Override
    public Student getStudentWithLeastCredits() throws MyException {
        Connection conn = null;
        try {
            conn = DataSource.getInstance().getConnection();
            String sql = "select st.*, sum(credits) as Total from exercise.student st\n" +
                    "\tjoin student_subject ss on ss.studentId = st.id\n" +
                    "    join subject su on su.id = ss.subjectId\n" +
                    "    group by studentId\n" +
                    "    order by Total ASC\n" +
                    "    limit 1";
            PreparedStatement pr = conn.prepareStatement(sql);
            ResultSet rs = pr.executeQuery();
            rs.next();
            Long id = rs.getLong("id");
            String name =  rs.getString("name");
            String surname = rs.getString("surname");
            Student st = new Student(id, name, surname);
            return st;
        } catch (Exception e) {
            throw new MyException("");
        } finally {
            DataSource.getInstance().closeConnection(conn);
        }
    }

    @Override
    public List<Student> getAllStudentPerFaculty(Long facultyId) throws MyException {
        Connection conn = null;
        List<Student> studentList = null;
        try {
            conn = DataSource.getInstance().getConnection();
            String sql = "SELECT st.* FROM exercise.student st\n" +
                    "inner join faculty f on f.id = st.facultyId\n" +
                    "where f.id = ?";
            PreparedStatement pr = conn.prepareStatement(sql);
            pr.setLong(1, facultyId);
            ResultSet rs = pr.executeQuery();
            studentList = new ArrayList<>();
            while (rs.next()) {
                Long id = rs.getLong("id");
                String name = rs.getString("name");
                String surname = rs.getString("surname");
                Student student =  new Student(id, name, surname);
                studentList.add(student);
            }
            return  studentList;
        } catch (Exception e) {
            throw new MyException("");
        } finally {
            DataSource.getInstance().closeConnection(conn);
        }
    }

    @Override
    public List<Student> getAllStudentPerUniversity(Long universityId) throws MyException {
        Connection conn = null;
        List<Student> studentList = null;
        try {
            conn = DataSource.getInstance().getConnection();
            String sql = "SELECT st.* FROM exercise.student st\n" +
                    "inner join faculty f on f.id = st.facultyId\n" +
                    "inner join university u on u.id = f.universityId\n" +
                    "where u.id = ?";
            PreparedStatement pr = conn.prepareStatement(sql);
            pr.setLong(1, universityId);
            ResultSet rs = pr.executeQuery();
            studentList = new ArrayList<>();
            while (rs.next()) {
                Long id = rs.getLong("id");
                String name = rs.getString("name");
                String surname = rs.getString("surname");
                Student student =  new Student(id, name, surname);
                studentList.add(student);
            }
            return  studentList;
        } catch (Exception e) {
            throw new MyException("");
        } finally {
            DataSource.getInstance().closeConnection(conn);
        }
    }
}
