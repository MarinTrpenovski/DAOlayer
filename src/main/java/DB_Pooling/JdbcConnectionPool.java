package DB_Pooling;

import constant.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by marin.trpenovski on 5/11/2017.
 */
public class JdbcConnectionPool {

    List<Connection> availableConnection = new ArrayList<>();

    public JdbcConnectionPool (){
        initializeConnectionPool();
    }

    private Connection createNewConnectionFromPool(){

        Configuration config = Configuration.getInstance();
        try {
            Class.forName(config.DB_DRIVER);
            Connection conn = (Connection) DriverManager.getConnection(config.DB_URL, config.DB_USER_NAME, config.DB_PASSWORD);
            return conn;
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Error while creating connection to DB " + e.getMessage());
        }
        return null;
    }

    private void initializeConnectionPool(){

        while (!checkIfConnectionPollIsFull()){
            availableConnection.add(createNewConnectionFromPool());
        }
    }

    private synchronized boolean checkIfConnectionPollIsFull(){
        final int MAX_POOL_SIZE = Configuration.getInstance().DB_MAX_CONNECTIONS;
        if(this.availableConnection.size() < MAX_POOL_SIZE){
            return false;
        } else {
            return true;
        }
    }

    public synchronized Connection getConnectionFromPool(){
        Connection connection = null;
        if (this.availableConnection.size() > 0) {
            connection = (Connection) availableConnection.get(0);
            this.availableConnection.remove(0);
        }
        return connection;
    }

    public synchronized void returnConnectionToPool(Connection conn){
        availableConnection.add(conn);
    }

    public List<Connection> getAvailableConnection(){
        return this.availableConnection;
    }
}
