package dao.impl;

import connections.DefaultConnectionFactory;
import dao.StudentDAO;
import dao.generic.GenericDAO;
import entity.Student;

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

    public List<Student> getAll() {
        List<Student> studentList = null;
        Connection conn;
        try {
            conn = DefaultConnectionFactory.getInstance().getConnection();
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

        }
        return studentList;

    }

    public Student getOne(Long id) {

        Student student = null;
        Connection conn;
        try {
            conn = DefaultConnectionFactory.getInstance().getConnection();
            PreparedStatement pr  = conn.prepareStatement("select * from student as st where st.id = ?");
            pr.setString(1, id.toString());
            ResultSet rs = pr.executeQuery();
            rs.next();
            student = new Student(rs.getLong("id"), rs.getString("name"), rs.getString("surname"));
            conn.close();

        } catch(Exception e){
            System.out.println("Error while fetching student " + e.getMessage());
        }
        return student;
    }

    public void delete(Long id) {
        Connection conn;
        try {
            conn = DefaultConnectionFactory.getInstance().getConnection();
            PreparedStatement pr  = conn.prepareStatement("delete from student where student.id = ?");
            pr.setLong(1, id);
            int number = pr.executeUpdate();
            conn.close();

        } catch(Exception e){
            System.out.println("Error while deleting student " + e.getMessage());
        }
    }

    public void update(Student student) {
        Connection conn;
        try {
            conn = DefaultConnectionFactory.getInstance().getConnection();
            PreparedStatement pr  = conn.prepareStatement("update student set name = ? , surname = ? where student.id = ?");
            pr.setString(1, student.getName());
            pr.setString(2, student.getSurname());
            pr.setLong(3, student.getId());
            int number = pr.executeUpdate();
            conn.close();

        } catch(Exception e){
            System.out.println("Error while updating student " + e.getMessage());
        }
    }

    public void create(Student student) {
        Connection conn;
        try {
            conn = DefaultConnectionFactory.getInstance().getConnection();
            PreparedStatement pr  = conn.prepareStatement("insert into student (name, surname) VALUES ( ?, ?)");
            pr.setString(1, student.getName());
            pr.setString(2, student.getSurname());
            int number = pr.executeUpdate();
            conn.close();

        } catch(Exception e){
            System.out.println("Error while creating student " + e.getMessage());
        }
    }

    @Override
    public void average(String columnName, String tableName) {
        Connection conn;
        try {
            conn = DefaultConnectionFactory.getInstance().getConnection();
            PreparedStatement pr  = conn.prepareStatement("select avg("+ columnName + ") as avg from " + tableName);
            ResultSet rs = pr.executeQuery();
            while (rs.next()) {
                System.out.println("average sum is  " + rs.getInt("avg"));
            }
            conn.close();

        } catch(Exception e){
            System.out.println("Error while calculating average " + e.getMessage());
        }

    }

    @Override
    public void averageWithCondition(String columnName, String tableName, String condition) {
        Connection conn;
        try {
            conn = DefaultConnectionFactory.getInstance().getConnection();
            PreparedStatement pr  = conn.prepareStatement("select avg("+ columnName + ") as avg from " + tableName + " " + condition);
            ResultSet rs = pr.executeQuery();
            while (rs.next()) {
                System.out.println("average is  " + rs.getInt("avg"));
            }
            conn.close();

        } catch(Exception e){
            System.out.println("Error while calculating average " + e.getMessage());
        }
    }

    @Override
    public void getAllRecords(String columnName, String tableName) {
        Connection conn;
        try {
            conn = DefaultConnectionFactory.getInstance().getConnection();
            PreparedStatement pr  = conn.prepareStatement("select count("+ columnName + ") as total from " + tableName);
            ResultSet rs = pr.executeQuery();
            while (rs.next()) {
                System.out.println("number of record is  " + rs.getInt("total"));
            }

        } catch(Exception e){
            System.out.println("Error while calculating number of record " + e.getMessage());
        }
    }

    @Override
    public void getAllRecordsWithCondition(String columnName, String tableName, String condition) {

        Connection conn;
        try {
            conn = DefaultConnectionFactory.getInstance().getConnection();
            PreparedStatement pr  = conn.prepareStatement("select count("+ columnName + ") as total from " + tableName + " " + condition);
            ResultSet rs = pr.executeQuery();
            while (rs.next()) {
                System.out.println("number of record is  " + rs.getInt("total"));
            }

        } catch(Exception e){
            System.out.println("Error while creating student " + e.getMessage());
        }


    }

    @Override
    public void getMaxRecord(String columnName, String tableName) {
        Connection conn;
        try {
            conn = DefaultConnectionFactory.getInstance().getConnection();
            PreparedStatement pr  = conn.prepareStatement("select max(" + columnName +") as max from " + tableName);
            ResultSet rs = pr.executeQuery();
            while (rs.next()) {
                System.out.println("max record is  " + rs.getInt("max"));
            }
        } catch (Exception e) {
            System.out.println("Error while fetching max record " + e.getMessage());
        }
    }

    @Override
    public void getMinRecord(String columnName, String tableName) {
        Connection conn;
        try {
            conn = DefaultConnectionFactory.getInstance().getConnection();
            PreparedStatement pr  = conn.prepareStatement("select min(" + columnName +") as min from " + tableName);
            ResultSet rs = pr.executeQuery();
            while (rs.next()) {
                System.out.println("min record is  " + rs.getInt("min"));
            }
        } catch (Exception e) {
            System.out.println("Error while fetching min record " + e.getMessage());
        }
    }

    @Override
    public void getMaxRecordWithCondition(String columnName, String tableName, String condition) {
        Connection conn;
        try {
            conn = DefaultConnectionFactory.getInstance().getConnection();
            PreparedStatement pr  = conn.prepareStatement("select max(" + columnName +") as max from " + tableName + " " + condition);
            ResultSet rs = pr.executeQuery();
            while (rs.next()) {
                System.out.println("max record is  " + rs.getInt("max"));
            }
        } catch (Exception e) {
            System.out.println("Error while fetching max record " + e.getMessage());
        }
    }

    @Override
    public void getMinRecordWithCondition(String columnName, String tableName, String condition) {
        Connection conn;
        try {
            conn = DefaultConnectionFactory.getInstance().getConnection();
            PreparedStatement pr  = conn.prepareStatement("select min(" + columnName +") as min from " + tableName + " " + condition);
            ResultSet rs = pr.executeQuery();
            while (rs.next()) {
                System.out.println("max record is  " + rs.getInt("min"));
            }
        } catch (Exception e) {
            System.out.println("Error while fetching min record " + e.getMessage());
        }
    }
}
