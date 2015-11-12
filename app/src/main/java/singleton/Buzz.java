package singleton;

import org.joda.time.DateTime;
import util.Utility;

import java.io.Serializable;

/**
 * Created by Neil on 13/10/2015.
 */
public class Buzz implements Serializable{

    private int user_id;
    private int id;
    private String incoming_url;
    private String outcoming_url;
    private String name;
    private String script;
    private String created_at;
    private String updated_at;
    private String buzz_key;

    public Buzz(int user_id, int id, String incoming_url, String outcoming_url, String name, String script, String created_at, String updated_at, String buzz_key){
        this.user_id = user_id;
        this.id = id;
        this.incoming_url = incoming_url;
        this.outcoming_url = outcoming_url;
        this.name = name;
        this.script = script;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.buzz_key = buzz_key;
    }

    @Override
    public String toString(){
        return "userID: " + user_id + ", id: " + id + ", name: " + name + ", lastBuzz: " + updated_at + ", buzzKey: " + buzz_key;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIncoming_url() {
        return incoming_url;
    }

    public void setIncoming_url(String incoming_url) {
        this.incoming_url = incoming_url;
    }

    public String getOutcoming_url() {
        return outcoming_url;
    }

    public void setOutcoming_url(String outcoming_url) {
        this.outcoming_url = outcoming_url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getScript() {
        return script;
    }

    public void setScript(String script) {
        this.script = script;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public String getBuzz_key() {
        return buzz_key;
    }

    public void setBuzz_key(String buzz_key) {
        this.buzz_key = buzz_key;
    }

    public String getTimeSinceBuzz(){
        DateTime dt;
        if(updated_at != null){
           dt =  new DateTime(updated_at);
        }else{
            dt = new DateTime(created_at);
        }
        return Utility.timeSinceBuzz(dt);
    }
}
