package dao.impl;

import DBCP_DB_Pooling.DataSource;
import connections.DefaultConnectionFactory;
import dao.FacultyDAO;
import dao.generic.GenericDAO;
import entity.Faculty;
import entity.Student;
import exception.MyException;

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
public class FacultyDAOImpl implements FacultyDAO {

    @Override
    public List<Faculty> getAll() throws MyException {
        List<Faculty> facultyList = null;
        Connection conn = null;
        try {
            conn = DataSource.getInstance().getConnection();
            PreparedStatement pr  = conn.prepareStatement("select * from faculty");
            facultyList =  new ArrayList<>();
            ResultSet rs = pr.executeQuery();
            while (rs.next()){
                Long id = rs.getLong("id");
                Long parentId = rs.getLong("parentId");
                Long parentIndex = rs.getLong("parentIndex");
                String numericalMapping = rs.getString("numericalmapping");
                Long depth = rs.getLong("depth");
                String name = rs.getString("name");
                String address = rs.getString("address");
                Long universityId = rs.getLong("universityId");
                Faculty faculty =  new Faculty(id, parentId, parentIndex, numericalMapping, depth,name, address, universityId);
                facultyList.add(faculty);

            }

            return facultyList;
        } catch (SQLException ex){
            throw new MyException("");
        } finally {
            DataSource.getInstance().closeConnection(conn);
        }
    }

    @Override
    public void delete(Long id) throws  MyException {
        Connection conn = null;
        try {
            conn = DefaultConnectionFactory.getInstance().getConnection();
            PreparedStatement pr  = conn.prepareStatement("delete from faculty where faculty.id = ?");
            pr.setLong(1, id);
            int number = pr.executeUpdate();
            conn.close();

        } catch(Exception e){
            throw new MyException("");
        } finally {
            DataSource.getInstance().closeConnection(conn);
        }
    }

    @Override
    public void update(Faculty faculty) throws MyException {
        Connection conn = null;
        try {
            conn = DefaultConnectionFactory.getInstance().getConnection();
            PreparedStatement pr  = conn.prepareStatement("update faculty set parentId = ?, parentIndex = ?, nummericalMapping = ?, depth = ?, name = ? , address = ?, universityId = ?, where student.id = ?");
            pr.setLong(1, faculty.getParentId());
            pr.setLong(2, faculty.getParentIndex());
            pr.setString(3, faculty.getNumericalMapping());
            pr.setLong(4, faculty.getDepth());
            pr.setString(5, faculty.getName());
            pr.setString(6, faculty.getAddress());
            pr.setLong(7, faculty.getUniversityId());
            int number = pr.executeUpdate();
            conn.close();

        } catch(Exception e){
            throw new MyException("");
        } finally {
            DataSource.getInstance().closeConnection(conn);
        }
    }

    @Override
    public void create(Faculty faculty) throws MyException {
        Connection conn = null;
        try {
            conn = DefaultConnectionFactory.getInstance().getConnection();
            PreparedStatement pr  = conn.prepareStatement("insert into faculty (parentId, parentIndex, nummericalMapping, depth, name, address, universityId) VALUES ( ?, ?, ?, ?, ?, ?, ?)");
            pr.setLong(1, faculty.getParentId());
            pr.setLong(2, faculty.getParentIndex());

            pr.setString(3, faculty.getNumericalMapping());
            pr.setLong(4, faculty.getDepth());
            pr.setString(5, faculty.getName());
            pr.setString(6, faculty.getAddress());
            pr.setLong(7, faculty.getUniversityId());
            int number = pr.executeUpdate();
            conn.close();

        } catch(Exception e){
            throw new MyException("");
        } finally {
            DataSource.getInstance().closeConnection(conn);
        }
    }

    @Override
    public Faculty getOne(Long id) throws MyException {

        Faculty faculty = null;
        Connection conn = null;
        try {
            conn = DefaultConnectionFactory.getInstance().getConnection();
            PreparedStatement pr  = conn.prepareStatement("select * from faculty as fac where fac.id = ?");
            pr.setLong(1, id);
            ResultSet rs = pr.executeQuery();
            rs.next();
            faculty = new Faculty(rs.getLong("id"),rs.getLong("parentId"), rs.getLong("parentIndex"), rs.getString("numericalmapping"), rs.getLong("depth"), rs.getString("name"), rs.getString("address"), rs.getLong("universityId"));
            conn.close();
            return faculty;
        } catch(Exception e){
            throw new MyException("");

        } finally {
            DataSource.getInstance().closeConnection(conn);
        }

    }

    @Override
    public List<Student> getAllStudentForFaculty(Long id) throws MyException {
        Connection conn = null;
        List<Student> studentList = null;
        try {
            conn = DataSource.getInstance().getConnection();
            String sql = "SELECT * FROM exercise.student where facultyId = ?";
            PreparedStatement pr = conn.prepareStatement(sql);
            pr.setLong(1, id);
            ResultSet rs  = pr.executeQuery();
            studentList =  new ArrayList<>();
            while (rs.next()) {
                Long studentId = rs.getLong("id");
                String name = rs.getString("name");
                String surname = rs.getString("surname");
                Student student =  new Student(studentId, name, surname);
                studentList.add(student);
            }
            System.out.println("Number of students per faculty  is " + studentList.size());
            return studentList;
        } catch (Exception e) {
            throw new MyException("");
        } finally {
            DataSource.getInstance().closeConnection(conn);
        }
    }

    @Override
    public List<Faculty> getOrderFaculties() throws MyException {
        Connection conn = null;
        List<Faculty> facultyList = null;
        try {
            conn = DataSource.getInstance().getConnection();
            String sql = "SELECT f1.name  as level1, f1.id as id, f1.parentId as parentId, \n" +
                    "f2.name as level2, f2.id as id, f2.parentId as parentId, \n" +
                    "f3.name as level3, f3.id as id , f3.parentId as parentId \n" +
                    "FROM exercise.faculty f1\n" +
                    "left join faculty f2 on f1.id = f2.parentId\n" +
                    "left join faculty f3 on f3.parentId = f2.id\n" +
                    "where f2.name is not null";
            PreparedStatement pr = conn.prepareStatement(sql);
            ResultSet rs = pr.executeQuery();
            facultyList = new ArrayList<>();
            while (rs.next()) {
                Long id = rs.getLong("id");
                Long parentId = rs.getLong("parentId");
                String name = rs.getString("level1");
                Faculty faculty  = new Faculty(id, parentId, name);
                facultyList.add(faculty);
            }
            return facultyList;
        } catch (Exception e){
            throw new MyException("");
        } finally {
            DataSource.getInstance().closeConnection(conn);
        }
    }

    @Override
    public Faculty getFacultyWithHighestAverageGrade() throws MyException {
        Connection conn = null;
        Faculty faculty = null;
        try {
            conn = DataSource.getInstance().getConnection();
            String sql = "SELECT f.* , avg(grade) as AverageGrade FROM exercise.faculty f\n" +
                    "\tjoin student s on s.facultyId = f.id\n" +
                    "    join student_subject ss on s.id = ss.studentId\n" +
                    "    group by facultyId\n" +
                    "    order by AverageGrade DESC\n" +
                    "    LIMIT 1;";
            PreparedStatement pr = conn.prepareStatement(sql);
            ResultSet rs = pr.executeQuery();
            rs.next();
            faculty = new Faculty(rs.getLong("id"),rs.getLong("parentId"), rs.getLong("parentIndex"), rs.getString("numericalmapping"), rs.getLong("depth"), rs.getString("name"), rs.getString("address"), rs.getLong("universityId"));
            return faculty;
        }catch (Exception e){
            throw new MyException("");
        } finally {
            DataSource.getInstance().closeConnection(conn);
        }
    }

    @Override
    public Faculty getFacultyWithLowerAverageGrade() throws MyException {

        Connection conn = null;
        Faculty faculty = null;
        try {
            conn = DataSource.getInstance().getConnection();
            String sql = "SELECT f.* , avg(grade) as AverageGrade FROM exercise.faculty f\n" +
                    "\tjoin student s on s.facultyId = f.id\n" +
                    "    join student_subject ss on s.id = ss.studentId\n" +
                    "    group by facultyId\n" +
                    "    order by AverageGrade ASC\n" +
                    "    LIMIT 1;";
            PreparedStatement pr = conn.prepareStatement(sql);
            ResultSet rs = pr.executeQuery();
            rs.next();
            faculty = new Faculty(rs.getLong("id"),rs.getLong("parentId"), rs.getLong("parentIndex"), rs.getString("numericalmapping"), rs.getLong("depth"), rs.getString("name"), rs.getString("address"), rs.getLong("universityId"));
            return faculty;
        }catch (Exception e){
            throw new MyException("");
        } finally {
            DataSource.getInstance().closeConnection(conn);
        }
    }
}
