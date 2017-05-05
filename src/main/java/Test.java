import connections.DefaultConnectionFactory;
import dao.impl.StudentDAOImpl;
import entity.Student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by marin.trpenovski on 4/19/2017.
 */
public class Test {




    public static void main(String[] args) {
        StudentDAOImpl studentDAO = new StudentDAOImpl();
        Student st = new Student();
        //studentDAO.update(st);
        System.out.println("Test");
        DefaultConnectionFactory connection = DefaultConnectionFactory.getInstance();
        try {
            Integer id = 3;
            Connection conn = connection.getConnection();
            PreparedStatement pr  = conn.prepareStatement("delete from student where student.id = ?");
            pr.setLong(1, id);
            int number = pr.executeUpdate();
            //rs.next();
            //Student student = new Student(rs.getLong("id"), rs.getString("name"), rs.getString("surname"));
            System.out.println("Connected " + number);

        } catch (Exception e) {
            System.out.println("Exception is" + e.getMessage());
        }
    }
}
