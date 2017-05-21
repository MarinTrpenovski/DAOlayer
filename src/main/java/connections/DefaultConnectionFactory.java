package connections;

import constant.DBConstants;
import exception.MyException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by marin.trpenovski on 4/20/2017.
 */
public class DefaultConnectionFactory {

    private static DefaultConnectionFactory connectionFactory = null;

    private DefaultConnectionFactory(){
        try {
            Class.forName(DBConstants.DB_DRIVER);
        } catch ( ClassNotFoundException e) {
            System.out.println("Class Not found exception" + e.getMessage());
        }
    }

    public Connection getConnection() throws MyException {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(DBConstants.URL, DBConstants.USERNAME, DBConstants.PASSWORD);
        } catch (SQLException e) {
            throw new MyException("databaseConnectionError");
        }

        return conn;
    }

    public static DefaultConnectionFactory getInstance () {
        if (connectionFactory == null){
            connectionFactory = new DefaultConnectionFactory();
        }
        return connectionFactory;
    }
}
