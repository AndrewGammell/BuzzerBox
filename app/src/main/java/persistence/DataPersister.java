package persistence;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import com.google.gson.Gson;
import holder.DataHolder;
import settings.Settings;
import singleton.User;

import java.util.List;


/**
 * Created by Devstream on 30/09/2015.
 */
public class DataPersister {
    private static final String PACKAGE_NAME = "io.buzzerbox.app";
    private static final String USER = "users";
    private static final String SETTINGS = "SETTINGS";


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
        preference.edit().remove(PACKAGE_NAME + USER).apply();
    }

//    public static boolean saveSettings(Context context){
//
//        Gson gson = new Gson();
//        String json = gson.toJson(DataHolder.getDataHolder().getSettingsList(context));
//        PreferenceManager.getDefaultSharedPreferences(context)
//                .edit()
//                .putString(PACKAGE_NAME + SETTINGS, json)
//                .apply();
//        return true;
//    }
//
//    public static void loadSettings(Context context) {
//        SharedPreferences preference = PreferenceManager.getDefaultSharedPreferences(context);
//        Gson gson = new Gson();
//        String json = preference.getString(PACKAGE_NAME + SETTINGS, null);
//
//
//        if(json != null)
//            DataHolder.getDataHolder().getSettingsList(context).addAll(gson.fromJson(json, List.class));
////            return null;
//
////        return gson.fromJson(json, List.class).;
//    }
//
//    public static void deleteSettings(Context context){
//        SharedPreferences preference = PreferenceManager.getDefaultSharedPreferences(context);
//        preference.edit().remove(PACKAGE_NAME + SETTINGS).apply();
//    }
}
