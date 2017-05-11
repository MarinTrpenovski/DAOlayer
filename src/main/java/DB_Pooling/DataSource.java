package DB_Pooling;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by marin.trpenovski on 5/11/2017.
 */
public class DataSource {

    static JdbcConnectionPool pool = new JdbcConnectionPool();

    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        Connection conn = pool.getConnectionFromPool();
        return conn;
    }

    public static void returnConnection(Connection conn){
        pool.returnConnectionToPool(conn);
    }
}
