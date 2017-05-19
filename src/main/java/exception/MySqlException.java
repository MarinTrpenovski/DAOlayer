package exception;

import java.sql.SQLException;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Created by marin.trpenovski on 5/19/2017.
 */
public class MySqlException extends SQLException {

    private String name;

    private Locale locale = new Locale("en", "EN");

    private ResourceBundle message = ResourceBundle.getBundle("SqlException",locale);

    public MySqlException(String exceptionName){
        this.name = this.message.getString(exceptionName);
    }

    public MySqlException (){

    }

    public String getName(){
        return this.name;
    }
}
