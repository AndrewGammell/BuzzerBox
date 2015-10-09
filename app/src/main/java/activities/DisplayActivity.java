package activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
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
    private Bundle bundle;

    private final FragmentManager fragmentManager = getSupportFragmentManager();
    private FragmentTransaction transaction;
    private final String TAG = "fragment";

    private ActionBar mActionBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mActionBar = getSupportActionBar();

        if(getIntent().getBundleExtra(BUNDLE_KEY) != null){
            bundle = getIntent().getBundleExtra(BUNDLE_KEY);
            obj = bundle.getSerializable(OBJECT_KEY);
        }
        displayDetailedView(obj);
    }

    private void displayDetailedView(Object obj){
        transaction = fragmentManager.beginTransaction();
        transaction.add(getContainer(), DetailedViewFragment.newInstance(obj), TAG).commit();
//        getSupportFragmentManager()
//                .beginTransaction()
//                .add(R.id.activity_main_container, DetailedViewFragment.newInstance(obj))
//                .commit();
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

    @Override
    public void setCurrentTitle(String title){
        mActionBar.setTitle(title);
    }

    private void goToLogin(){
            Intent intent = new Intent(this,MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            this.finish();
    }

    private int getContainer(){
        return R.id.activity_main_container;
    }
}