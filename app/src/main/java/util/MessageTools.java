package util;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by Devstream on 30/09/2015.
 */
public class MessageTools {

    public static void showLongToast(Context context,String message){
        Toast.makeText(context, message,Toast.LENGTH_LONG).show();
    }

    public static void showShortToast(Context context,String message){
      Toast.makeText(context, message,Toast.LENGTH_SHORT).show();
    }


}
