package tester;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Devstream on 29/09/2015.
 */
public class DummyUsers implements Serializable{

    int id;
    String username;
    String password;

    public DummyUsers(int id, String username, String password){
        this.id = id;
        this.username = username;
        this.password = password;
    }


    public static List<DummyUsers> initialiseDummies(){
        List<DummyUsers> list = new ArrayList<DummyUsers>();

        list.add(new DummyUsers(3423,"MickFlinn", "Flinno"));
        list.add(new DummyUsers(24,"superman","123456"));
        list.add(new DummyUsers(843,"tim","timmy"));
        list.add(new DummyUsers(1446,"andrew","alan"));

        return list;
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}