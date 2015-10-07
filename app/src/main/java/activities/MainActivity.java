package activities;


import abstracts.AbstractActivity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import fragments.DetailedViewFragment;
import fragments.ListOverViewFragment;
import fragments.OverviewFragment;
import fragments.SplashFragment;
import io.buzzerbox.app.R;
import persistence.DataPersister;
import util.MessageTools;
import util.Utility;

import java.util.Locale;


public class MainActivity extends AbstractActivity implements View.OnClickListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());
        displaySplash();
    }




    @Override
    protected int getContainer() {
        return R.id.activity_main_container;
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }

    private void displaySplash(){
        Log.d("TAG","displaySplash() in MainActivity");
        if(!(isFragmentDisplayed())){
            addFragment(SplashFragment.newInstance());
        }
    }

    @Override
    public void onClick(View view) {
        Log.d("TAG","onClick in MainActivity");
        switch(view.getId()){
            case R.id.btn_login:
                if(Utility.isValidUser(this)){
                    MessageTools.showLongToast(this,"User was validated");

                    replaceWithFragment(ListOverViewFragment.newInstance());
                }else{
                    MessageTools.showLongToast(this,"not a valid user");
                }
                break;
            case R.id.txt_placeholder: Utility.logout(this);
                break;
            case R.id.btn_create_account: MessageTools.showShortToast(this,"Fire up WebView to create account");
                break;
            case R.id.btn_forgot_password: MessageTools.showShortToast(this, "Fire up WebView to reset password");
                break;
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d("TAG", "Main Activity onStop()");
        persistFragment();
        DataPersister.saveUser(this);
    }

    @Override
    public void onBackPressed(){
        Fragment fragment = getFragment();
        if(fragment instanceof DetailedViewFragment){
            replaceWithFragment(ListOverViewFragment.newInstance());
        }
        if(fragment instanceof  ListOverViewFragment){
            this.finish();
        }
    }


}
