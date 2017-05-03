import connections.DefaultConnectionFactory;

import java.sql.Connection;

/**
 * Created by marin.trpenovski on 4/19/2017.
 */
public class Test {

    public static void main(String[] args) {
        System.out.println("Test");
        DefaultConnectionFactory connection = DefaultConnectionFactory.getInstance();
        try {
            Connection conn = connection.getConnection();
            System.out.println("Connected ");
        } catch (Exception e) {
            System.out.println("Exception is" + e.getMessage());
        }
    }
}
