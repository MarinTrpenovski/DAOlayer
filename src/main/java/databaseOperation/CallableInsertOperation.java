package databaseOperation;

import DBCP_DB_Pooling.DataSource;
import exception.MyException;
import jdk.nashorn.internal.codegen.CompilerConstants;

import java.sql.*;
import java.util.concurrent.Callable;

/**
 * Created by marin.trpenovski on 6/8/2017.
 */
public class CallableInsertOperation implements Callable {

    private String name;

    public CallableInsertOperation (String name) {
        this.name = name;
    }

    @Override
    public Object call() throws Exception {
        Connection conn = null;
        Long generatedId = null;
        try {
            conn = DataSource.getInstance().getConnection();
            PreparedStatement pr  = conn.prepareStatement("insert into university (name, location) VALUES ( ?, ?)", Statement.RETURN_GENERATED_KEYS);
            pr.setString(1, Thread.currentThread().getName());
            pr.setString(2, "Location " + this.name);
            int number = pr.executeUpdate();
            ResultSet rs = pr.getGeneratedKeys();
            if(rs.next()){
                System.out.println("Key is " + rs.getLong(1));
                generatedId =  rs.getLong(1);
            }
            System.out.println("NNumber is " + number);
        } catch (SQLException | MyException e) {

        } finally {
            try {
                DataSource.getInstance().closeConnection(conn);
            } catch (MyException e) {
                System.out.println("SQL Exception " + e.getMessage());
            }

        }
        return generatedId;
    }
}
