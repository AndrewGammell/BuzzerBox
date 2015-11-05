package abstracts;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import interfaces.ViewController;

import java.io.Serializable;

/**
 * Created by Devstream on 29/09/2015.
 */
public abstract class AbstractActivity extends FragmentActivity implements ViewController {

    protected abstract int getContainer();

    protected abstract int getLayout();



    private final FragmentManager fragmentManager = getSupportFragmentManager();
    private FragmentTransaction transaction;
    private final String TAG = "fragment";


    @Override
    public void replaceWithFragment(Fragment fragment) {
        Log.d("TAG","replaceWithFragment() in AbstractActivity");
        transaction = fragmentManager.beginTransaction();
        transaction.replace(getContainer(), fragment, TAG).commit();
    }

    public void addFragment(Fragment fragment) {
        Log.d("TAG","addFragment() in AbstractActivity");
        transaction = fragmentManager.beginTransaction();
        transaction.add(getContainer(), fragment, TAG).commit();
    }

    @Override
    public boolean isFragmentDisplayed() {
        Log.d("TAG","isFragmentDisplayed() in AbstractActivity");
        Fragment fragment = fragmentManager.findFragmentByTag(TAG);
        if (fragment == null) {
            return false;
        }
        return true;
    }


}