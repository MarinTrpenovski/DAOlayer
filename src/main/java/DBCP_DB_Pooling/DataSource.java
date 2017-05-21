package DBCP_DB_Pooling;

import constant.DBConstants;
import exception.MyException;
import org.apache.commons.dbcp.BasicDataSource;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by marin.trpenovski on 5/11/2017.
 */
public class DataSource {

    private static DataSource dataSource;

    private BasicDataSource ds;

    private DataSource() throws IOException, SQLException, PropertyVetoException {
        ds = new BasicDataSource();
        ds.setDriverClassName(DBConstants.DB_DRIVER);
        ds.setUsername(DBConstants.USERNAME);
        ds.setPassword(DBConstants.PASSWORD);
        ds.setUrl(DBConstants.URL);

        ds.setMinIdle(4);
        ds.setMaxIdle(6);
        ds.setMaxActive(6);
        ds.setMaxOpenPreparedStatements(180);
    }

    public static DataSource getInstance() throws MyException {

        try {
            if (dataSource == null) {
                dataSource = new DataSource();
                return dataSource;
            } else {
                return dataSource;
            }
        } catch (SQLException | PropertyVetoException | IOException e) {
            throw new MyException("databaseConnectionError");
        }

    }

    public synchronized Connection getConnection() throws MyException{
        try {
            return this.ds.getConnection();
        } catch (SQLException e) {
            throw new MyException("databaseConnectionError");
        }

    }

    public synchronized void closeConnection(Connection conn) throws MyException{

        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e){
                System.out.println("Error while closing connection " + e.getMessage());
            }
        }

    }

}
