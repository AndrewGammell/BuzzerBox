package activities;

import abstracts.AbstractActivity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import fragments.DetailedViewFragment;
import io.buzzerbox.app.R;

/**
 * Created by Devstream on 09/11/2015.
 */
public class DetailedViewActivity extends AbstractActivity {

    private String BUNDLE_KEY = "BUNDLE";
    private String OBJECT_KEY = "OBJECT";
    private Object obj;
    private Bundle bundle;


    @Override
    protected int getContainer() {
        return R.id.activity_main_container;
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }


    public void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(getLayout());
            if (getIntent().getBundleExtra(BUNDLE_KEY) != null) {
                bundle = getIntent().getBundleExtra(BUNDLE_KEY);
                obj = bundle.getSerializable(OBJECT_KEY);
                }

        if (!isFragmentDisplayed()) {
          displayFragment();
        }

    }


    private void displayFragment() {
        Fragment fragment = DetailedViewFragment.newInstance(obj);
        addFragment(fragment);
    }
}
