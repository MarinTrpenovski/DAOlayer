package databaseOperation;

/**
 * Created by marin.trpenovski on 5/19/2017.
 */
public class MultiThreadDatabaseOperation {

    public static void main(String[] args) {

        for(int i = 0; i < 100; i ++) {
            RunnableCRUDOperation r1 = new RunnableCRUDOperation("Thread - " + i);
            r1.start();
        }
    }
}
