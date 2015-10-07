package util;

import android.util.Log;

import java.util.concurrent.*;

/**
 * Created by Devstream on 29/09/2015.
 */
public class ThreadTools {

    private static ExecutorService service = Executors.newCachedThreadPool();
    private static Future<Object> future;
    private static final String TAG ="TAG";


    public static void runOnThread(Runnable runnable){
        service.submit(runnable);
    }

    public static Object runCallable(Callable callable){
        future = service.submit(callable);

        try{
            return future.get();
        }catch(InterruptedException ie){
            ie.printStackTrace();
            Log.d(TAG, "InterruptedException thrown in UtilityThread" + ie.getCause());
        }catch(ExecutionException ee){
            ee.printStackTrace();
            Log.d(TAG,"ExecutionException thrown in UtilityThread"+ ee.getCause());
        }
        return null;
    }
}
