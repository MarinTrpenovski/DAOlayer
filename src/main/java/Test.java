import DB_Pooling.DataSource;
import DB_Pooling.JdbcConnectionPool;
import connections.DefaultConnectionFactory;
import dao.impl.StudentDAOImpl;
import entity.Student;

import javax.xml.crypto.Data;
import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by marin.trpenovski on 4/19/2017.
 */
public class Test {

    /*public static void main(String[] args) throws SQLException, ClassNotFoundException, IOException, PropertyVetoException {
        DBCP_DB_Pooling.DataSource ds = DBCP_DB_Pooling.DataSource.getInstance();
        for (int i = 0; i < 7; i ++) {
            DBCP_DB_Pooling.DataSource.getInstance().getConnection();
            System.out.println("Number of idle are " + ds.getNumIdle());
        }

    }*/


    public static void main(String[] args) {

        StudentDAOImpl studentDAO = new StudentDAOImpl();
        Student st = new Student();
        //studentDAO.update(st);
        System.out.println("Test");
        DefaultConnectionFactory connection = DefaultConnectionFactory.getInstance();
        try {
            Integer id = 3;
            Connection conn = connection.getConnection();
            PreparedStatement pr  = conn.prepareStatement("select avg(credits) as average from subject where subject.id = 1 or subject.id = 3");

            ResultSet rs = pr.executeQuery();
            while (rs.next()) {
                System.out.println("from while " + rs.getInt("average"));
            }
            //rs.next();
            //Student student = new Student(rs.getLong("id"), rs.getString("name"), rs.getString("surname"));
            System.out.println("Connected " + rs.getStatement());

        } catch (Exception e) {
            System.out.println("Exception is" + e.getMessage());
        }
    }
}
