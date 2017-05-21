package databaseOperation;

import DBCP_DB_Pooling.DataSource;
import exception.MyException;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by marin.trpenovski on 5/19/2017.
 */
public class RunnableCRUDOperation implements Runnable{

    private String name;
    private Thread t;

    public RunnableCRUDOperation (String name) {
        this.name = name;
        System.out.println("Creating " + name);
    }

    @Override
    public void run() {
        System.out.println("Running " + name);
        Connection conn = null;
        try {
            conn = DataSource.getInstance().getConnection();
            PreparedStatement pr  = conn.prepareStatement("insert into university (name, location) VALUES ( ?, ?)");
            pr.setString(1, "New " + this.name);
            pr.setString(2, "Location " + this.name);
            int number = pr.executeUpdate();
            System.out.println("NNumber is " + number);
        } catch (SQLException | MyException e) {

        } finally {
            try {
                DataSource.getInstance().closeConnection(conn);
            } catch (MyException e) {
                System.out.println("SQL Exception " + e.getMessage());
            }

        }
    }

    public void start () {
        System.out.println("Starting " + name);
        if(t == null){
            t = new Thread (this, name);
            t.start();
        }
    }
}
