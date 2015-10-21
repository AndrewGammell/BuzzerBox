package activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import fragments.DetailedViewFragment;
import fragments.WebViewFragment;
import io.buzzerbox.app.R;
import util.MessageTools;
import util.Utility;

/**
 * Created by Devstream on 06/10/2015.
 */
public class DisplayActivity extends AppCompatActivity implements DetailedViewFragment.Callback {
    private String BUNDLE_KEY = "BUNDLE";
    private String OBJECT_KEY = "OBJECT";
    private String CALL_KEY = "CALL";
    private Object obj;
    private Bundle bundle;
    private int call;

    private final FragmentManager fragmentManager = getSupportFragmentManager();
//    private FragmentTransaction transaction;
    private final String TAG = "fragment";
    private ActionBar mActionBar;


    /**
     * Gets the Bundle from the Intent Extras, then gets the int(CALL_KEY) from the Bundle
     * and stores it in the int variable call.
     * Checks if
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mActionBar = getSupportActionBar();

        if(getIntent().getBundleExtra(BUNDLE_KEY) != null){
            bundle = getIntent().getBundleExtra(BUNDLE_KEY);
            if(bundle.getInt(CALL_KEY, -1) > -1){
                call = bundle.getInt(CALL_KEY,-1);
            }
            if(bundle.getSerializable(OBJECT_KEY) != null){
                obj = bundle.getSerializable(OBJECT_KEY);
            }
        }

        if(!isFragmentDisplayed()){
            displayFragment(call);
        }

    }

//    private void displayDetailedView(Object obj){
//        transaction = fragmentManager.beginTransaction();
//        transaction.add(getContainer(), DetailedViewFragment.newInstance(obj), TAG).commit();
//    }

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

    private boolean isFragmentDisplayed(){
        Fragment fragment = fragmentManager.findFragmentByTag(TAG);
        if(fragment != null){
            return true;
        }
        return false;
    }

    private void displayFragment(int call){
        Fragment fragment = null;
        switch(call){
            case 0: fragment = DetailedViewFragment.newInstance(obj);
                break;
            case 1: fragment = WebViewFragment.newInstance(call);
                break;
            case 2: fragment = WebViewFragment.newInstance(call);
                break;
            default: MessageTools.showShortToast(this,"nothing to show");

        }
        fragmentManager.beginTransaction().add(getContainer(),fragment, TAG).commit();

    }
}