package activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import fragments.DetailedViewFragment;
import io.buzzerbox.app.R;
import util.MessageTools;
import util.Utility;

/**
 * Created by Devstream on 06/10/2015.
 */
public class DisplayActivity extends AppCompatActivity implements DetailedViewFragment.Callback {
    private String BUNDLE_KEY = "BUNDLE";
    private String OBJECT_KEY = "OBJECT";
    private Object obj;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Bundle bundle;
        if(getIntent().getBundleExtra(BUNDLE_KEY) != null){
            bundle = getIntent().getBundleExtra(BUNDLE_KEY);
            obj = bundle.getSerializable(OBJECT_KEY);

        }
        displayDetailedView(obj);
    }

    private void displayDetailedView(Object obj){
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.activity_main_container, DetailedViewFragment.newInstance(obj))
                .commit();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.action_settings:
                Utility.logout(this);
                MessageTools.showShortToast(this, "logged out");
                goToLogin();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void goToLogin(){
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }
    
}