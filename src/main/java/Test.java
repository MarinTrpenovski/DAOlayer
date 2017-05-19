import dao.impl.FacultyDAOImpl;
import exception.MySqlException;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.SQLException;

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


    public static void main(String[] args) throws PropertyVetoException, IOException, SQLException, MySqlException {
        FacultyDAOImpl dao = new FacultyDAOImpl();
        dao.getAll();
        /*try {
            throw new SQLException();
        } catch (Exception e) {
            throw new MySqlException("insertException");
            //System.out.println("Exception value is " + e.getMessage());
        }*/

    }
}
