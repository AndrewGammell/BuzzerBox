package authentication;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import org.apache.commons.codec.binary.Base64;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import java.nio.charset.Charset;

import io.buzzerbox.app.R;
import singleton.Buzz;
import singleton.User;

/**
 * Created by Neil on 10/10/2015.
 */
public class LoginAsyncTask extends AppCompatActivity{

    private String email;
    private String password;
    private String url;
    private String responseJSONContent;
    private JSONObject responseJSON;
    private Post loading;
    private BuzzGet buzz;
    private String getBuzz = "https://app.buzzerbox.net/api/buzzes/index";
    private String baseURL = "https://app.buzzerbox.net/api/auth/new";
    private String AuthToken;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_splash);
        JSONObject dets = new JSONObject();
        this.execute(dets.toString());
    }

    public LoginAsyncTask(){}

    public LoginAsyncTask(String email, String password){
        this.email = email;
        this.password = password;
        this.url = this.baseURL+"?email="+this.email+"&password="+this.password;
    }

    public void execute(String jsonContent){
        loading = new Post();
        loading.execute(jsonContent);
    }

    private void getBuzz(){
        buzz = new BuzzGet();
        buzz.execute("");
    }

    class Post extends AsyncTask<String, String, Integer> {
        @Override
        protected Integer doInBackground(String... args){
            int statusCode;
            // Here we make first contact with server
            // We send a post with email and password and receive an auth token back
            try{
                HttpClient client = new DefaultHttpClient();
                HttpPost request = new HttpPost(url);
                HttpEntity entity = new StringEntity(args[0]);
                try{
                    HttpResponse response = client.execute(request);
                    HttpEntity responseEntity = response.getEntity();
                    statusCode = response.getStatusLine().getStatusCode();
                    responseJSONContent = EntityUtils.toString(responseEntity);
                    responseJSON = new JSONObject(responseJSONContent);
                    AuthToken = responseJSON.getString("authentication_token");
                    return statusCode;
                } catch(Exception e){
                    return null;
                }
            } catch(Exception e){
                return null;
            }
        }

        @Override
        protected void onPostExecute(Integer resultCode){
            getBuzz();
        }
    }

    class BuzzGet extends AsyncTask<String, String, Integer> {
        @Override
        protected Integer doInBackground(String... args){
            int statusCode;
            String preEncode = email + ":" + AuthToken;
            byte[] bytesEncoded = Base64.encodeBase64(preEncode.getBytes(Charset.forName("US-ASCII")));

            String postEncode = new String(bytesEncoded);

            try{
                HttpClient client = new DefaultHttpClient();
                HttpGet request = new HttpGet(getBuzz);
                // Using Basic Authentication. We send the base 64 encoded string (email:token) to id ourselves
                String auth = "Basic "+ postEncode;
                request.setHeader("Authorization", auth);

                try{
                    HttpResponse response = client.execute(request);
                    HttpEntity responseEntity = response.getEntity();
                    statusCode = response.getStatusLine().getStatusCode();

                    // get json string back
                    responseJSONContent = EntityUtils.toString(responseEntity);

                    // Here we perform some parsing as the data Javi sends back is not correct json
                    // I'm sure there's a better and more robust way to do this but for now this is a solution
                    String s = responseJSONContent.replace("\\\"", "\"");
                    if (s.charAt(0)=='"'){ s = s.substring(1); }
                    if (s.charAt(s.length()-1)=='"'){ s = s.substring(0, s.length()-1); }

                    // Convert our json to a json Array
                    JsonParser  parser = new JsonParser();
                    JsonElement elem   = parser.parse(s);
                    JsonArray buzzes = elem.getAsJsonArray();

                    // Iterate through json array and populate a Buzz class for each entry
                    for (int i = 0; i < buzzes.size(); i++){
                        // create new buzz class and map json
                        Buzz entry = (new Gson()).fromJson(buzzes.get(i).toString(), Buzz.class);

                        // add mapped class to the User singleton's Buzz List
                        User.getUser().getBuzzList().add(entry);
                    }
                   //responseJSON = new JSONObject(responseJSONContent);
                    return statusCode;
                } catch(Exception e){
                    Log.d("NEIL", "bad connection");
                    return null;
                }
            } catch(Exception e){
                Log.d("NEIL", "some other err");
                return null;
            }
        }

        @Override
        protected void onPostExecute(Integer resultCode){

        }
    }
}
