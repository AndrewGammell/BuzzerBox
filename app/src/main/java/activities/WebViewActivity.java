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


    //     get the container that holds the fragment.
    protected int getContainer() {
        return R.id.activity_main_container;
    }

    //    get the layout to be inflated.
    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }


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