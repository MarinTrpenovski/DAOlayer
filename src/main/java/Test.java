import dao.impl.UniversityDAOImpl;
import databaseOperation.CallableInsertOperation;
import entity.University;
import exception.MyException;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;


/**
 * Created by marin.trpenovski on 4/19/2017.
 */
public class Test {

    public static void main(String[] args) throws Exception {
        ExecutorService executor = Executors.newFixedThreadPool(1);
        Callable<Long> task = new CallableInsertOperation("TestInsert");
        Future<Long> future = executor.submit(task);
        System.out.println("Future is done ? " + future.isDone());
        Long result = future.get();

        System.out.println("Future is done ? " + future.isDone());
        System.out.println("result : " + result);
    }
}
