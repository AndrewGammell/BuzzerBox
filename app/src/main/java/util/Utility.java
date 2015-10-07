package util;

import android.content.Context;
import io.buzzerbox.app.R;
import singleton.User;
import tester.Database;
import tester.DummyUsers;

import java.util.ArrayList;
import java.util.List;

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

public static List makeList(Context context){
    List list = new ArrayList();


    return DummyUsers.initialiseDummies();
}

}
