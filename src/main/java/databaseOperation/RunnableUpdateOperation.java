package databaseOperation;

import DBCP_DB_Pooling.DataSource;
import connections.DefaultConnectionFactory;
import entity.University;
import exception.MyException;

import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 * Created by marin.trpenovski on 5/28/2017.
 */
public class RunnableUpdateOperation implements Runnable{

    private Long id;

    public RunnableUpdateOperation(Long id){
        this.id = id;
    }

    @Override
    public void run() {
        Connection conn = null;
        University university = new University("Updated " + Thread.currentThread().getName() , "Skopje Updated");
        try {
            conn = DefaultConnectionFactory.getInstance().getConnection();
            PreparedStatement pr  = conn.prepareStatement("update university set name = ?, location = ? where university.id = ?");
            pr.setString(1, university.getName());
            pr.setString(2, university.getLocation());
            pr.setLong(3, this.id);
            pr.execute();
            conn.close();

        } catch(Exception e){

        } finally {
            try {
                DataSource.getInstance().closeConnection(conn);
            } catch (MyException e) {
                System.out.println("SQL Exception " + e.getMessage());
            }
        }
    }
}
