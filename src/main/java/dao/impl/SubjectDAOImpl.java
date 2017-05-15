package dao.impl;

import DBCP_DB_Pooling.DataSource;
import connections.DefaultConnectionFactory;
import dao.SubjectDAO;
import dao.generic.GenericDAO;
import entity.Student;
import entity.Subject;

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
    public List<Subject> getAll() throws PropertyVetoException, SQLException, IOException {

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
            System.out.println("Exception while fetching all subjects " + ex.getMessage());
        } finally {
            DataSource.getInstance().closeConnection(conn);
        }
        return subjects;

    }

    @Override
    public void delete(Long id) throws PropertyVetoException, SQLException, IOException {
        Connection conn = null;
        try {
            conn = DefaultConnectionFactory.getInstance().getConnection();
            PreparedStatement pr  = conn.prepareStatement("delete from subject where subject.id = ?");
            pr.setLong(1, id);
            int number = pr.executeUpdate();
        } catch(Exception e){
            System.out.println("Error while deleting subject " + e.getMessage());
        } finally {
            DataSource.getInstance().closeConnection(conn);
        }
    }

    @Override
    public void update(Subject subject) throws PropertyVetoException, SQLException, IOException {
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
            System.out.println("Error while updating subject" + e.getMessage());
        } finally {
            DataSource.getInstance().closeConnection(conn);
        }
    }

    @Override
    public void create(Subject subject) throws PropertyVetoException, SQLException, IOException {
        Connection conn = null;
        try {
            conn = DefaultConnectionFactory.getInstance().getConnection();
            PreparedStatement pr  = conn.prepareStatement("insert into subject (name, credits, semester) VALUES ( ?, ?, ?)");
            pr.setString(1, subject.getName());
            pr.setLong(2, subject.getCredits());
            pr.setLong(3, subject.getSemester());
            int number = pr.executeUpdate();
        } catch(Exception e){
            System.out.println("Error while creating subject " + e.getMessage());
        } finally {
            DataSource.getInstance().closeConnection(conn);
        }
    }

    @Override
    public Subject getOne(Long id) throws PropertyVetoException, SQLException, IOException {

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
            System.out.println("Error while fetching subject " + e.getMessage());
        } finally {
            DataSource.getInstance().closeConnection(conn);
        }
        return subject;
    }
}
