package dao.impl;

import connections.DefaultConnectionFactory;
import dao.UniversityDAO;
import dao.generic.GenericDAO;
import entity.Faculty;
import entity.Subject;
import entity.University;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by marin.trpenovski on 5/9/2017.
 */
public class UniversityDAOImpl implements UniversityDAO {

    @Override
    public List<University> getAll() {
        List<University> universities = null;
        Connection conn;
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
            conn.close();
            return universities;
        } catch (Exception ex){
            System.out.println("Excetion while fetching all universities " + ex.getMessage());
        }
        return universities;
    }

    @Override
    public void delete(Long id) {
        Connection conn;
        try {
            conn = DefaultConnectionFactory.getInstance().getConnection();
            PreparedStatement pr  = conn.prepareStatement("delete from university where university.id = ?");
            pr.setLong(1, id);
            int number = pr.executeUpdate();
            conn.close();

        } catch(Exception e){
            System.out.println("Error while deleting university " + e.getMessage());
        }
    }

    @Override
    public void update(University university) {
        Connection conn;
        try {
            conn = DefaultConnectionFactory.getInstance().getConnection();
            PreparedStatement pr  = conn.prepareStatement("update university set name = ?, location = ?, where university.id = ?");
            pr.setString(1, university.getName());
            pr.setString(2, university.getLocation());
            pr.setLong(3, university.getId());
            int number = pr.executeUpdate();
            conn.close();

        } catch(Exception e){
            System.out.println("Error while updating university" + e.getMessage());
        }
    }

    @Override
    public void create(University university) {
        Connection conn;
        try {
            conn = DefaultConnectionFactory.getInstance().getConnection();
            PreparedStatement pr  = conn.prepareStatement("insert into university (name, location) VALUES ( ?, ?)");
            pr.setString(1, university.getName());
            pr.setString(2, university.getLocation());
            int number = pr.executeUpdate();
            conn.close();

        } catch(Exception e){
            System.out.println("Error while creating university " + e.getMessage());
        }
    }

    @Override
    public University getOne(Long id) {

        University university = null;
        Connection conn;
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
            System.out.println("Error while fetching university " + e.getMessage());
        }
        return university;

    }
}
