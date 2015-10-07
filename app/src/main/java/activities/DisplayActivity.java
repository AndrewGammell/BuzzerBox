package activities;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import fragments.DetailedViewFragment;
import io.buzzerbox.app.R;

/**
 * Created by Devstream on 06/10/2015.
 */
public class DisplayActivity extends FragmentActivity {
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
}