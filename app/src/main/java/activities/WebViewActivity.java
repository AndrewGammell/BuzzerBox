package activities;

import abstracts.AbstractActivity;
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
public class WebViewActivity extends AbstractActivity {
    private String BUNDLE_KEY = "BUNDLE";
    private String OBJECT_KEY = "OBJECT";
    private String CALL_KEY = "CALL";
    private Object obj;
    private Bundle bundle;
    private int call = -1;

    //     get the container that holds the fragment.
    protected int getContainer() {
        return R.id.activity_main_container;
    }

    //    get the layout to be inflated.
    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }

    //   Gets the Bundle from the Intent Extras, then gets the int(CALL_KEY) from the Bundle
//   and stores it in the int variable call.
//   Checks if it was also passed an Object(OBJECT_KEY) in the Bundle, if an Object was passed in it
//   is stored in the Object variable obj.
//   calls displayFragment() if no fragment is found.
    @Override
   public void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(getLayout());

        if (!isFragmentDisplayed()) {
            displayFragment();
        }

    }

    //   decides what fragment to show based on the CALL_KEY int passed in.
    private void displayFragment() {


        addFragment(WebViewFragment.newInstance());
    }
}