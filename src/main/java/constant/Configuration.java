package constant;

/**
 * Created by marin.trpenovski on 5/11/2017.
 */
public class Configuration {

    public String DB_USER_NAME;

    public String DB_PASSWORD;

    public String DB_URL;

    public String DB_DRIVER;

    public Integer DB_MAX_CONNECTIONS;

    private void init(){
        DB_USER_NAME = DBConstants.USERNAME;
        DB_PASSWORD = DBConstants.PASSWORD;
        DB_DRIVER = DBConstants.DB_DRIVER;
        DB_URL = DBConstants.URL;
        DB_MAX_CONNECTIONS = DBConstants.MAX_CONNECTION;
    }

    public Configuration (){
        init();
    }

    private static Configuration configuration = new Configuration();

    public static Configuration getInstance(){
        return  configuration;
    }

}
