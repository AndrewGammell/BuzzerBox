package persistence;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;
import com.google.gson.Gson;
import singleton.User;


/**
 * Created by Devstream on 30/09/2015.
 *
 */
public class DataPersister {
    private static final String PACKAGE_NAME = "io.buzzerbox.app";
    private static final String USER = "users";


    public static User loadUser(Context context) {
        SharedPreferences preference = PreferenceManager.getDefaultSharedPreferences(context);
        Gson gson = new Gson();
        String json = preference.getString(PACKAGE_NAME + USER, null);

        if(json == null)
            return null;

        return gson.fromJson(json,User.class);
    }


    public static boolean saveUser(Context context) {
        Log.d(USER, "User is stored");
        User user = User.getInstance(context);
        Gson gson = new Gson();
        String json = gson.toJson(user);
        PreferenceManager.getDefaultSharedPreferences(context)
                .edit()
                .putString(PACKAGE_NAME+USER, json)
                .apply();
        return true;

    }

    public static void deleteUser(Context context){
        SharedPreferences preference = PreferenceManager.getDefaultSharedPreferences(context);
        preference.edit().remove(PACKAGE_NAME+USER).apply();
    }

}
