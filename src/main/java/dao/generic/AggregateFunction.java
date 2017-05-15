package dao.generic;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by marin.trpenovski on 5/10/2017.
 */
public interface AggregateFunction {

    void average(String columnName, String tableName) throws PropertyVetoException, SQLException, IOException;

    void averageWithCondition(String columnName, String tableName, String condition) throws PropertyVetoException, SQLException, IOException;

    void getAllRecords(String semesterName, String tableName) throws PropertyVetoException, SQLException, IOException;

    void getAllRecordsWithCondition(String columnName, String tableName, String condition) throws PropertyVetoException, SQLException, IOException;

    void getMaxRecord(String columnName, String tableName) throws PropertyVetoException, SQLException, IOException;

    void getMinRecord(String columnName, String tableName) throws PropertyVetoException, SQLException, IOException;

    void getMaxRecordWithCondition(String columnName, String tableName, String condition) throws PropertyVetoException, SQLException, IOException;

    void getMinRecordWithCondition(String columnName, String tableName, String condition) throws PropertyVetoException, SQLException, IOException;

    void getSum(String columnName, String tableName);

    void getSumWithCondition(String columnName, String tableName, String condition);

}
