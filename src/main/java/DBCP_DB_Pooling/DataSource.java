package DBCP_DB_Pooling;

import constant.DBConstants;
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

    public static DataSource getInstance() throws IOException, SQLException, PropertyVetoException {
        if (dataSource == null) {
            dataSource = new DataSource();
            return dataSource;
        } else {
            return dataSource;
        }
    }

    public synchronized Connection getConnection() throws SQLException{

        return this.ds.getConnection();
    }

    public int getNumIdle () {
        return this.ds.getNumActive();
    }

}
