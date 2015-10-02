package activities;


import abstracts.AbstractActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import fragments.OverviewFragment;
import fragments.SplashFragment;
import io.buzzerbox.app.R;
import persistence.DataPersister;
import util.MessageTools;
import util.Utility;


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
                    replaceWithFragment(OverviewFragment.newInstance());
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
}
