package persistence;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import com.google.gson.Gson;
import holder.DataHolder;
import singleton.User;


/**
 * Created by Devstream on 30/09/2015.
 */
public class DataPersister {
    private static final String PACKAGE_NAME = "io.buzzerbox.app";
    private static final String USER = "users";
    private static final String DATAHOLDER = "DATAHOLDER";


    public static User loadUser(Context context) {
        SharedPreferences preference = PreferenceManager.getDefaultSharedPreferences(context);
        Gson gson = new Gson();
        String json = preference.getString(PACKAGE_NAME + USER, null);

        if(json == null)
            return null;

        return gson.fromJson(json,User.class);
    }

    public static boolean saveUser(Context context) {
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

//    public static boolean saveDataHolder(Context context){
//        DataHolder holder = DataHolder.getDataHolder(context);
//        Gson gson = new Gson();
//        String json = gson.toJson(holder);
//        PreferenceManager.getDefaultSharedPreferences(context)
//                .edit()
//                .putString(PACKAGE_NAME+DATAHOLDER, json)
//                .apply();
//        return true;
//    }

//    public static DataHolder loadDataHolder(Context context) {
//        SharedPreferences preference = PreferenceManager.getDefaultSharedPreferences(context);
//        Gson gson = new Gson();
//        String json = preference.getString(PACKAGE_NAME + DATAHOLDER, null);
//
//        if(json == null)
//            return null;
//
//        return gson.fromJson(json,DataHolder.class);
//    }
}
