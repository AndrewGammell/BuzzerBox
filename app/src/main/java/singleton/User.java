package singleton;

import android.content.Context;
import persistence.DataPersister;

import java.io.Serializable;

/**
 * Created by Devstream on 29/09/2015.
 */
public class User implements Serializable{
    private static User user;
    private String username;
    private String password;

    private User(){
    }

    public static synchronized User getInstance(Context context){
        if(user == null){
            if(DataPersister.loadUser(context)!= null){
                user = DataPersister.loadUser(context);
            }else{
                user = new User();
            }

        }
        return user;
    }

    public String getUsername(){
        return username;
    }

    public String getPassword(){
        return password;
    }

    public void setUsername(String username){
        this.username = username;
    }

    public void setPassword(String password){
        this.password = password;
    }


}
