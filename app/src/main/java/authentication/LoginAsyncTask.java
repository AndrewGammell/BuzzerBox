package authentication;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;

import org.apache.commons.codec.binary.Base64;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.nio.charset.Charset;

import io.buzzerbox.app.R;
import persistence.DataPersister;
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
    private boolean tokenExpired = false;
    private boolean hasEmailPass = false;
    private boolean hasAuth = false;
    private int tries;

    private Context c;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_splash);
        JSONObject dets = new JSONObject();
        this.execute(dets.toString());
    }

    public LoginAsyncTask(){}

    public LoginAsyncTask(String email, String password, Context c){
        this.email = email;
        this.password = password;
        this.url = this.baseURL+"?email="+this.email+"&password="+this.password;
        this.c = c;
        this.tries = 0;
    }

    public void execute(String jsonContent){
        //loading = new Post();
       // loading.execute(jsonContent);
        Log.d("NEIL", "auth_ " + User.getInstance(c).getAuthToken());
        Log.d("NEIL", "pw " + User.getInstance(c).getPassword());
        Log.d("NEIL", "em " + User.getInstance(c).getUsername());
        checkSavedStatus();
    }

    private void getBuzz(){
        buzz = new BuzzGet();
        buzz.execute("");
    }

    private void checkSavedStatus(){
        Log.d("NEIL", "checking saved status");
        if (User.getInstance(c).getAuthToken().equals("null")){
            hasAuth = false;
            Log.d("NEIL", "no auth pass");
            // no auth token, check for username and pass
            if ((User.getInstance(c).getUsername().equals("null"))||(User.getInstance(c).getPassword().equals("null"))){
                Log.d("NEIL", "no email pass");
                hasEmailPass = false;
                // no username or pass token, go to Login Screen
                    //Intent login =
            }
            else{
                // Send email and pass and get new token
                Log.d("NEIL", "has email pass");
                hasEmailPass = true;
                this.email = User.getInstance(c).getUsername();
                this.password = User.getInstance(c).getPassword();
                this.url = this.baseURL+"?email="+this.email+"&password="+this.password;
                Log.d("NEIL", this.url);
                loading = new Post();
                loading.execute("");
            }
        }
        else{
            hasAuth = true;
            // Log in with auth token, when doing this - on response we should check if token expired,
            // and if so, send email and pass again
            Log.d("NEIL", "has auth");
            AuthToken = User.getInstance(c).getAuthToken();

            getBuzz();
        }
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
                Log.d("NEIL", url);
                HttpEntity entity = new StringEntity(args[0]);
                try{
                    HttpResponse response = client.execute(request);
                    HttpEntity responseEntity = response.getEntity();
                    statusCode = response.getStatusLine().getStatusCode();
                    responseJSONContent = EntityUtils.toString(responseEntity);
                    Log.d("NEIL", "resp json content " + responseJSONContent);
                    responseJSON = new JSONObject(responseJSONContent);
                    Log.d("NEIL", "json token " + responseJSON);
                    AuthToken = responseJSON.getString("authentication_token");
                    Log.d("NEIL", "auth token " + AuthToken);
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
            Log.d("NEIL", "saving auth token " + AuthToken);
            User.getInstance(c).setUsername(email);
            User.getInstance(c).setPassword(password);
            User.getInstance(c).setAuthToken(AuthToken);
            DataPersister.saveUser(c);
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
                    Log.d("NEIL", "response json " + s);
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
                        User.getInstance(c).getBuzzHolder().add(entry);
                        User.getInstance(c).addToBuzzTypes(entry);

                        Log.d("NEIL", entry.toString());
                    }
                    tries++;
                    tokenExpired = false;
                   //responseJSON = new JSONObject(responseJSONContent);
                    return statusCode;
                } catch(Exception e){
                    // Need to put in place more solid checks for expired token in return json above
                    Log.d("NEIL", "Token Expired");
                    tokenExpired = true;
                    return null;
                }
            } catch(Exception e){
                Log.d("NEIL", "some other err");
                return null;
            }
        }

        @Override
        protected void onPostExecute(Integer resultCode){
            // If the auth token has expired, run login process again

            // auth token save data set to null, if we have email and pass we run login again
            // if not we go to login screen
            if (tokenExpired) {
                User.getInstance(c).setAuthToken("null");
                tokenExpired = false;
                if (hasEmailPass){
                    // only allow 5 automated tries before going to login.
                    // we'll add in a check for error in json response too
                    if (tries < 5){
                        loading = new Post();
                        loading.execute("");
                    }
                    else{
                        // go to login screen
                        Log.d("NEIL", "tried 5 times, going to login");
                    }
                }
                else{
                    // go to login screen
                    Log.d("NEIL", "no email and p, going to login");
                }
            }
            else{
                DataPersister.saveUser(c);
            }
        }
    }
}
