package exception;

import enumeration.LocaleEnum;
import enumeration.TranslationEnum;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Created by marin.trpenovski on 5/19/2017.
 */
public class MyException extends Exception {

    private String exceptionName;

    private ResourceBundle resourceBundle;

    public MyException (String exceptionName){
        super(exceptionName);
        this.resourceBundle = ResourceBundle.getBundle("SqlException", LocaleEnum.getLocale(LocaleEnum.ENGLISH));
        this.exceptionName = this.resourceBundle.getString(exceptionName);
        System.out.println("Error is :" + this.getExceptionName());
    }

    public String getExceptionName() {
        return this.exceptionName;
    }
}
