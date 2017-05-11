package dao.generic;

/**
 * Created by marin.trpenovski on 5/10/2017.
 */
public interface AggregateFunction {

    void average(String columnName, String tableName);

    void averageWithCondition(String columnName, String tableName, String condition);

    void getAllRecords(String semesterName, String tableName);

    void getAllRecordsWithCondition(String columnName, String tableName, String condition);

    void getMaxRecord(String columnName, String tableName);

    void getMinRecord(String columnName, String tableName);

    void getMaxRecordWithCondition(String columnName, String tableName, String condition);

    void getMinRecordWithCondition(String columnName, String tableName, String condition);

    void getSum(String columnName, String tableName);

    void getSumWithCondition(String columnName, String tableName, String condition);

}
