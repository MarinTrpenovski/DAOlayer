import connections.DefaultConnectionFactory;
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
        System.out.println("Test");
        DefaultConnectionFactory connection = DefaultConnectionFactory.getInstance();
        try {
            Integer id = 1;
            Connection conn = connection.getConnection();
            PreparedStatement pr  = conn.prepareStatement("select * from student where student.id = ?");
            pr.setInt(1, id);
            ResultSet rs = pr.executeQuery();
            rs.next();
            Student student = new Student(rs.getLong("id"), rs.getString("name"), rs.getString("surname"));
            System.out.println("Connected " + student.getId() + student.getName() + student.getSurname() );

        } catch (Exception e) {
            System.out.println("Exception is" + e.getMessage());
        }
    }
}
