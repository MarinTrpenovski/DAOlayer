package databaseOperation;

import DBCP_DB_Pooling.DataSource;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by marin.trpenovski on 5/19/2017.
 */
public class MultipleCRUDDatabaseOperation {

    public static void main(String[] args) throws PropertyVetoException, SQLException, IOException {

        for(int i = 0; i < 5; i ++) {
            Connection conn = null;
            try {
                conn = DataSource.getInstance().getConnection();
                PreparedStatement pr  = conn.prepareStatement("insert into university (name, location) VALUES ( ?, ?)");
                pr.setString(1, "New " + i);
                pr.setString(2, "Location " + i);
                int number = pr.executeUpdate();
                System.out.println("NNumber is " + number);
            } catch (SQLException | IOException | PropertyVetoException e) {
                System.out.println("SQL Exception " + e.getMessage());
            } finally {
                DataSource.getInstance().closeConnection(conn);
            }
        }
    }
}
