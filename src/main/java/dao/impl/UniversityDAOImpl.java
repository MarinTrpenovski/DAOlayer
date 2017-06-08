package dao.impl;

import DBCP_DB_Pooling.DataSource;
import connections.DefaultConnectionFactory;
import dao.UniversityDAO;
import dao.generic.GenericDAO;
import entity.Faculty;
import entity.Subject;
import entity.University;
import exception.MyException;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by marin.trpenovski on 5/9/2017.
 */
public class UniversityDAOImpl implements UniversityDAO {

    @Override
    public List<University> getAll() throws MyException {
        List<University> universities = null;
        Connection conn = null;
        try {
            conn = DefaultConnectionFactory.getInstance().getConnection();
            PreparedStatement pr  = conn.prepareStatement("select * from university");
            universities =  new ArrayList<>();
            ResultSet rs = pr.executeQuery();
            while (rs.next()){
                Long id = rs.getLong("id");
                String name = rs.getString("name");
                String location = rs.getString("location");
                University university =  new University(id, name, location);
                universities.add(university);

            }
            return universities;
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
            PreparedStatement pr  = conn.prepareStatement("delete from university where university.id = ?");
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
    public void update(University university) throws MyException {
        Connection conn = null;
        try {
            conn = DefaultConnectionFactory.getInstance().getConnection();
            PreparedStatement pr  = conn.prepareStatement("update university set name = ?, location = ? where university.id = ?");
            pr.setString(1, university.getName());
            pr.setString(2, university.getLocation());
            pr.setLong(3, university.getId());
            pr.execute();
            conn.close();

        } catch(Exception e){
            throw new MyException("");
        } finally {
            DataSource.getInstance().closeConnection(conn);
        }
    }

    @Override
    public void create(University university) throws MyException {
        Connection conn = null;
        try {
            conn = DefaultConnectionFactory.getInstance().getConnection();
            PreparedStatement pr  = conn.prepareStatement("insert into university (name, location) VALUES ( ?, ?)", Statement.RETURN_GENERATED_KEYS);
            pr.setString(1, university.getName());
            pr.setString(2, university.getLocation());
            int number = pr.executeUpdate();
            ResultSet rs = pr.getGeneratedKeys();
            if(rs.next()){
                System.out.println("Key is " + rs.getLong(1));
            }

            conn.close();

        } catch(Exception e){
            throw new MyException("");
        } finally {
            DataSource.getInstance().closeConnection(conn);
        }
    }

    @Override
    public University getOne(Long id) throws MyException {

        University university = null;
        Connection conn = null;
        try {
            conn = DefaultConnectionFactory.getInstance().getConnection();
            PreparedStatement pr  = conn.prepareStatement("select * from university as uni where uni.id = ?");
            pr.setLong(1, id);
            ResultSet rs = pr.executeQuery();
            rs.next();
            university = new University(rs.getLong("id"), rs.getString("name"), rs.getString("location"));
            conn.close();
            return university;
        } catch(Exception e){
            throw new MyException("");
        } finally {
            DataSource.getInstance().closeConnection(conn);
        }
    }

    @Override
    public List<Faculty> getFacultiesForUniversity(Long universityId) throws MyException {
        Connection conn = null;
        List<Faculty> facultyList = null;
        try {
            conn = DataSource.getInstance().getConnection();
            String sql = "SELECT * FROM exercise.faculty where universityId = ?";
            PreparedStatement pr = conn.prepareStatement(sql);
            pr.setLong(1, universityId);
            ResultSet rs = pr.executeQuery();
            facultyList = new ArrayList<Faculty>();
            while (rs.next()) {
                Long id = rs.getLong("id");
                Long parentId = rs.getLong("parentId");
                Long parentIndex = rs.getLong("parentIndex");
                String numericalMapping = rs.getString("numericalmapping");
                Long depth = rs.getLong("depth");
                String name = rs.getString("name");
                String address = rs.getString("address");
                Long universityIdReturned = rs.getLong("universityId");
                Faculty faculty =  new Faculty(id, parentId, parentIndex, numericalMapping, depth,name, address, universityIdReturned);
                facultyList.add(faculty);
            }
            return facultyList;
        } catch (Exception e) {
            throw new MyException("");
        } finally {
            DataSource.getInstance().closeConnection(conn);
        }
    }

    @Override
    public Integer getNumberOfStudentsForUniversity(Long universityId) throws MyException {
        Connection conn = null;
        try {
            conn = DataSource.getInstance().getConnection();
            String sql = "SELECT count(st.id) as numberOfStudents FROM exercise.student st\n" +
                    "inner join faculty f on f.id = st.facultyId\n" +
                    "inner join university u on u.id = f.universityId\n" +
                    "where universityId = 1";
            PreparedStatement pr = conn.prepareStatement(sql);
            pr.setLong(1, universityId);
            ResultSet rs = pr.executeQuery();
            rs.next();
            Integer numberOfStudentsAre = rs.getInt("numberOfStudents");
            System.out.println("Number of students are " + numberOfStudentsAre);
            return numberOfStudentsAre;
        } catch (Exception e) {
            throw new MyException("");
        } finally {
            DataSource.getInstance().closeConnection(conn);
        }
    }
}
