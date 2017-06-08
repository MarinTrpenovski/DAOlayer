package databaseOperation;

import DBCP_DB_Pooling.DataSource;
import exception.MyException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by marin.trpenovski on 5/28/2017.
 */
public class SQLInjectionScenario {

    public static void main(String[] args) {

    }

    private static void executeQuery() throws MyException{

        Connection conn = null;
        try {
            conn = DataSource.getInstance().getConnection();
            PreparedStatement pr = conn.prepareStatement("insert into university (name, location); DROP TABLE university VALUES ( ?, ?)");
            pr.executeQuery();

        } catch (SQLException e){
            System.out.println("Exception is " + e.getMessage());
            throw new MyException("");
        } finally {
            DataSource.getInstance().closeConnection(conn);
        }
    }

}
