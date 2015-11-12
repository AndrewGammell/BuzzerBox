package activities;

import abstracts.AbstractActivity;
import android.os.Bundle;
import fragments.WebViewFragment;
import io.buzzerbox.app.R;

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