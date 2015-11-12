package activities;

import abstracts.AbstractActivity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import fragments.DetailedViewFragment;
import io.buzzerbox.app.R;

/**
 * Created by Devstream on 09/11/2015.
 */
public class DetailedViewActivity extends AbstractActivity {

    private Object obj;


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
        String BUNDLE_KEY = "BUNDLE";
        if (getIntent().getBundleExtra(BUNDLE_KEY) != null) {
                Bundle bundle = getIntent().getBundleExtra(BUNDLE_KEY);
            String OBJECT_KEY = "OBJECT";
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
