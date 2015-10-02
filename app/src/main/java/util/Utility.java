package util;

import android.content.Context;
import singleton.User;
import tester.Database;
import tester.DummyUsers;

/**
 * Created by Devstream on 29/09/2015.
 */
public class Utility {


    public static boolean isValidUser(Context context){
        User user = User.getInstance(context);
        Database database = new Database(context);

        if(user.getUsername() == null && user.getPassword() == null)
            return false;

        if(database.callingAllDummies().size() == 0){
            for(DummyUsers dummy: DummyUsers.initialiseDummies()){
                database.addEntry(dummy);
            }
        }

        return database.verifyUser(user.getUsername(),user.getPassword());
    }

    public static void logout(Context context){
        User user = User.getInstance(context);
        user.setUsername(null);
        user.setPassword(null);
    }

}
