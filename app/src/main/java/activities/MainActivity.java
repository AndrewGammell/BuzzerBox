package activities;


import SQLLite.SettingsDatabase;
import abstracts.AbstractActivity;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import authentication.LoginAsyncTask;
import fragments.SplashFragment;
import holder.DataHolder;
import io.buzzerbox.app.R;
import singleton.User;
import util.MessageTools;
import util.Sorter;
import util.Utility;


public class MainActivity extends AbstractActivity implements View.OnClickListener {

    LoginAsyncTask login;
    private String BUNDLE_KEY = "BUNDLE";
    private String CALL_KEY = "CALL";


//    @Override
//    protected void onStart() {
//        super.onStart();
//        User.getInstance(this);
//        if(DataHolder.getDataHolder().getSettingsList().size() == 0){
//            new SettingsDatabase(this).loadSettings();
//        }
//
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());
        User.getInstance(this);
        if(DataHolder.getDataHolder().getSettingsList().size() == 0){
            new SettingsDatabase(this).runBackGroundLoader();
        }
        displaySplash();
        new Sorter(this).execute();

    }

    //    Used to hid the keypad when the screen is touched;
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        hideKeyboard(this);
        return super.onTouchEvent(event);
    }

    @Override
    protected int getContainer() {
        return R.id.activity_main_container;
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }

    private void displaySplash() {
        Log.d("NEIL", "displaySplash() in MainActivity");
        if (!(isFragmentDisplayed())) {
            addFragment(SplashFragment.newInstance());
        }
    }

    @Override
    public void onClick(View view) {
        Log.d("TAG", "onClick in MainActivity");
        switch (view.getId()) {
            case R.id.btn_login:
                if (Utility.isValidUser(this)) {
                    MessageTools.showLongToast(this, "User was validated");
                    startNewActivity();
                } else {
                    MessageTools.showLongToast(this, "not a valid user");
                }
                break;
            case R.id.btn_create_account:
                startWebActivity();
                MessageTools.showShortToast(this, "Fire up WebView to create account");
                break;
        }
    }

    //   Gets the current focus of the screen and if not null hides keypad;
    private static void hideKeyboard(Activity activity) {
        View view = activity.getCurrentFocus();
        if (view != null) {
            ((InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE)).
                    hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    private void startNewActivity(){
        Log.d("TAG", "Getting pushed now");
        startActivity(PageViewActivity.newIntent(this, LoginAsyncTask.buzzList));
        this.finish();
    }

//     Creates a Bundle and adds an int(CALL_KEY) to be put into the intent.
//     Fires off a new Activity with Args.

    private void startWebActivity() {
        Intent intent = new Intent(this, WebViewActivity.class);
        startActivity(intent);
    }
}