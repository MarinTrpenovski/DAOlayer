package databaseOperation;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Created by marin.trpenovski on 5/19/2017.
 */
public class MultiThreadDatabaseOperation {

    public static void main(String[] args) throws Exception{

        ExecutorService executorService = Executors.newFixedThreadPool(8);

        for(int i = 0; i < 8; i ++){
            Callable<Long> task = new CallableInsertOperation("Test Insert " + i);

            Future<Long> future = executorService.submit(task);
            Long result = future.get();

            if (future.isDone()){
                Runnable update = new RunnableUpdateOperation(result);
                executorService.execute(update);
            }

        }

        executorService.shutdown();
        while (!executorService.isTerminated()){

        }
    }

}
