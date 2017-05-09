package dao.impl;

import connections.DefaultConnectionFactory;
import dao.FacultyDAO;
import dao.generic.GenericDAO;
import entity.Faculty;
import entity.Student;

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
    public List<Faculty> getAll() throws SQLException {
        List<Faculty> facultyList = null;
        Connection conn;
        try {
            conn = DefaultConnectionFactory.getInstance().getConnection();
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
                Faculty faculty =  new Faculty(id, parentId, parentIndex, numericalMapping, depth,name, address);
                facultyList.add(faculty);

            }
            conn.close();
            return facultyList;
        } catch (Exception ex){
            System.out.println("Error while fetching Faculties " + ex.getMessage());
        }
        return facultyList;
    }

    @Override
    public void delete(Long id) {
        Connection conn;
        try {
            conn = DefaultConnectionFactory.getInstance().getConnection();
            PreparedStatement pr  = conn.prepareStatement("delete from faculty where faculty.id = ?");
            pr.setLong(1, id);
            int number = pr.executeUpdate();
            conn.close();

        } catch(Exception e){
            System.out.println("Error while deleting faculty " + e.getMessage());
        }
    }

    @Override
    public void update(Faculty faculty) {
        Connection conn;
        try {
            conn = DefaultConnectionFactory.getInstance().getConnection();
            PreparedStatement pr  = conn.prepareStatement("update faculty set parentId = ?, parentIndex = ?, nummericalMapping = ?, depth = ?, name = ? , address = ? where student.id = ?");
            pr.setLong(1, faculty.getParentId());
            pr.setLong(2, faculty.getParentIndex());
            pr.setString(3, faculty.getNumericalMapping());
            pr.setLong(4, faculty.getDepth());
            pr.setString(5, faculty.getName());
            pr.setString(6, faculty.getAddress());
            int number = pr.executeUpdate();
            conn.close();

        } catch(Exception e){
            System.out.println("Error while updating faculty" + e.getMessage());
        }
    }

    @Override
    public void create(Faculty faculty) {
        Connection conn;
        try {
            conn = DefaultConnectionFactory.getInstance().getConnection();
            PreparedStatement pr  = conn.prepareStatement("insert into faculty (parentId, parentIndex, nummericalMapping, depth, name, address) VALUES ( ?, ?, ?, ?, ?, ?)");
            pr.setLong(1, faculty.getParentId());
            pr.setLong(2, faculty.getParentIndex());

            pr.setString(3, faculty.getNumericalMapping());
            pr.setLong(4, faculty.getDepth());
            pr.setString(5, faculty.getName());
            pr.setString(6, faculty.getAddress());
            int number = pr.executeUpdate();
            conn.close();

        } catch(Exception e){
            System.out.println("Error while creating student " + e.getMessage());
        }
    }

    @Override
    public Faculty getOne(Long id) {

        Faculty faculty = null;
        Connection conn;
        try {
            conn = DefaultConnectionFactory.getInstance().getConnection();
            PreparedStatement pr  = conn.prepareStatement("select * from faculty as fac where fac.id = ?");
            pr.setLong(1, id);
            ResultSet rs = pr.executeQuery();
            rs.next();
            faculty = new Faculty(rs.getLong("id"),rs.getLong("parentId"), rs.getLong("parentIndex"), rs.getString("numericalmapping"), rs.getLong("depth"), rs.getString("name"), rs.getString("address"));
            conn.close();
            return faculty;
        } catch(Exception e){
            System.out.println("Error while fetching faculty " + e.getMessage());
        }
        return faculty;

    }
}
