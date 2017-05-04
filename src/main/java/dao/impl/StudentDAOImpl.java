package dao.impl;

import connections.DefaultConnectionFactory;
import dao.StudentDAO;
import dao.generic.DAOFactory;
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
public class StudentDAOImpl extends DAOFactory implements StudentDAO{

    public List<Student> getAll() throws SQLException {
        List<Student> studentList = null;
        Connection conn = null;
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
            conn.close();
        } finally {

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

        }
        return student;
    }

    public void delete(Long id) {

    }

    public void update(Long id) {

    }
}
