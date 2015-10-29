package abstracts;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import interfaces.ViewController;

/**
 * Created by Devstream on 29/09/2015.
 * Abstract Activity to be used as the base point of all activities using fragments.
 * Implementing ViewController interface allows instances of AbstractActivity
 * to be used to make callbacks to the replaceWithFragment,addFragment and isFragmentDisplayed methods.
 *
 */
public abstract class AbstractActivity extends FragmentActivity implements ViewController {

    private final FragmentManager fragmentManager = getSupportFragmentManager();
    private FragmentTransaction transaction;
    private final String TAG = "fragment";


    protected abstract int getContainer();

    protected abstract int getLayout();

    protected abstract void doInOnCreate(Bundle saveInstanceState);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());
        doInOnCreate(savedInstanceState);
    }

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