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


    public static void main(String[] args) throws PropertyVetoException, IOException, SQLException {
        Long id = 2L;
        StudentDAOImpl st =  new StudentDAOImpl();
        st.getStudentWithHighestGrade();
    }
}
