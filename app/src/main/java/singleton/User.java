package singleton;

import android.content.Context;
import android.util.Log;

import persistence.DataPersister;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Devstream on 29/09/2015.
 * describes a user with a list of Buzz's
 */
public class User implements Serializable{
    private static User user;
    private String username = "null";
    private String password = "null";
    private List<Buzz>buzzList = new ArrayList<Buzz>();

    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }

    private String authToken = "null";
    private List<Buzz> buzzHolder;
    private HashMap<String, List<Buzz>> buzzTypes;

    public HashMap<String, List<Buzz>> getBuzzTypes() {
        if (buzzTypes == null){
            buzzTypes = new HashMap<String, List<Buzz>>();
        }
        return buzzTypes;
    }

    public void setBuzzTypes(HashMap<String, List<Buzz>> buzzTypes) {
        this.buzzTypes = buzzTypes;
    }

    public void addToBuzzTypes(Buzz entry){
        if (User.getUser().getBuzzTypes().containsKey(entry.getBuzz_key())){
            User.getUser().getBuzzTypes().get(entry.getBuzz_key()).add(entry);
            Log.d("NEIL", "adding to buzz types " + entry.getBuzz_key() + ": " + entry.toString());
        }
        else{
            List<Buzz> newBuzzType = new ArrayList<Buzz>();
            newBuzzType.add(entry);
            User.getUser().getBuzzTypes().put(entry.getBuzz_key(), newBuzzType);
            Log.d("NEIL", "creating buzz type " + entry.getBuzz_key() + ": " + entry.toString());
        }
    }

    public List<Buzz> getBuzzHolder() {
        if (buzzHolder == null){
            buzzHolder = new ArrayList<Buzz>();
        }
        return buzzHolder;
    }

    public void setBuzzHolder(List<Buzz> buzzHolder) {
        this.buzzHolder = buzzHolder;
    }


    private User(){
    }

    public static User getUser(){
        if(user == null){
            user = new User();
        }
        return user;
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

    public void setList(List<Buzz> theBuzzList){
        this.buzzList = theBuzzList;
    }

    public List<Buzz>getBuzzList() {
        return buzzList;
    }
}
