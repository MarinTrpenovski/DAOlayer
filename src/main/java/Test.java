import exception.MyException;


/**
 * Created by marin.trpenovski on 4/19/2017.
 */
public class Test {

    /*public static void main(String[] args) throws SQLException, ClassNotFoundException, IOException, PropertyVetoException {
        DBCP_DB_Pooling.DataSource ds = DBCP_DB_Pooling.DataSource.getInstance();
        for (int i = 0; i < 7; i ++) {
            DBCP_DB_Pooling.DataSource.getInstance().getConnection();
            System.out.println("Number of idle are " + ds.getNumIdle());
        }

    }
*/

    public static void main(String[] args) throws  MyException {
        try {
            throw new MyException("databaseConnectionError");
        } catch (Exception e) {
            System.out.println("Exception is" + e.getMessage());
        }


        /*try {
            throw new SQLException();
        } catch (Exception e) {
            throw new MySqlException("insertException");
            //System.out.println("Exception value is " + e.getMessage());
        }*/

    }
}
