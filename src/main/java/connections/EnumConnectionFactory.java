package connections;

import constant.DBConstants;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by marin.trpenovski on 4/20/2017.
 */
public enum EnumConnectionFactory {

    INSTANCE;

    private EnumConnectionFactory (){
        try {
            Class.forName(DBConstants.DB_DRIVER);
        } catch ( ClassNotFoundException e) {
            System.out.println("Class Not found exception" + e.getMessage());
        }
    }

    public Connection getConnection() throws SQLException {
        Connection conn = null;
        conn = DriverManager.getConnection(DBConstants.URL, DBConstants.USERNAME, DBConstants.PASSWORD);
        return conn;
    }
}
