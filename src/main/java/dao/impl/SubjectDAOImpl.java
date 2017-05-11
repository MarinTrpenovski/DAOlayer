package dao.impl;

import connections.DefaultConnectionFactory;
import dao.SubjectDAO;
import dao.generic.GenericDAO;
import entity.Student;
import entity.Subject;

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
    public List<Subject> getAll() {

        List<Subject> subjects = null;
        Connection conn;
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
            System.out.println("Exception while fetching all subjects " + ex.getMessage());
        }
        return subjects;

    }

    @Override
    public void delete(Long id) {
        Connection conn;
        try {
            conn = DefaultConnectionFactory.getInstance().getConnection();
            PreparedStatement pr  = conn.prepareStatement("delete from subject where subject.id = ?");
            pr.setLong(1, id);
            int number = pr.executeUpdate();
            conn.close();

        } catch(Exception e){
            System.out.println("Error while deleting subject " + e.getMessage());
        }
    }

    @Override
    public void update(Subject subject) {
        Connection conn;
        try {
            conn = DefaultConnectionFactory.getInstance().getConnection();
            PreparedStatement pr  = conn.prepareStatement("update subject set name = ? , credits = ?, semester = ? where subject.id = ?");
            pr.setString(1, subject.getName());
            pr.setLong(2, subject.getCredits());
            pr.setLong(3, subject.getSemester());
            pr.setLong(4, subject.getId());
            int number = pr.executeUpdate();
            conn.close();

        } catch(Exception e){
            System.out.println("Error while updating subject" + e.getMessage());
        }
    }

    @Override
    public void create(Subject subject) {
        Connection conn;
        try {
            conn = DefaultConnectionFactory.getInstance().getConnection();
            PreparedStatement pr  = conn.prepareStatement("insert into subject (name, credits, semester) VALUES ( ?, ?, ?)");
            pr.setString(1, subject.getName());
            pr.setLong(2, subject.getCredits());
            pr.setLong(3, subject.getSemester());
            int number = pr.executeUpdate();
            conn.close();

        } catch(Exception e){
            System.out.println("Error while creating subject " + e.getMessage());
        }
    }

    @Override
    public Subject getOne(Long id) {

        Subject subject = null;
        Connection conn;
        try {
            conn = DefaultConnectionFactory.getInstance().getConnection();
            PreparedStatement pr  = conn.prepareStatement("select * from subject as st where st.id = ?");
            pr.setString(1, id.toString());
            ResultSet rs = pr.executeQuery();
            rs.next();
            subject = new Subject(rs.getLong("id"), rs.getString("name"), rs.getLong("credits"), rs.getLong("semester"));
            conn.close();

        } catch(Exception e){
            System.out.println("Error while fetching subject " + e.getMessage());
        }
        return subject;
    }
}
