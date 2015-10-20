package activities;


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
import io.buzzerbox.app.R;
import util.MessageTools;
import util.Utility;


public class MainActivity extends AbstractActivity implements View.OnClickListener {

    LoginAsyncTask login;
    private String BUNDLE_KEY = "BUNDLE";
    private String CALL_KEY = "CALL";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());
        login = new LoginAsyncTask("dummy@bundly.io", "dummy1234");
        login.execute("");
        displaySplash();

    }

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

                    //replaceWithFragment(ListOverViewFragment.newInstance())
                    startNewActivity();
                } else {
                    MessageTools.showLongToast(this, "not a valid user");
                }
                break;
            case R.id.btn_create_account:
                startWebActivity(1);
                MessageTools.showShortToast(this, "Fire up WebView to create account");
                break;
            case R.id.btn_forgot_password:
                startWebActivity(2);
                MessageTools.showShortToast(this, "Fire up WebView to reset password");
                break;
        }
    }

    public static void hideKeyboard(Activity activity) {
        View view = activity.getCurrentFocus();
        if (view != null) {
            ((InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE)).
                    hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    private void startNewActivity(){
        Intent intent = new Intent(this,PageViewActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        this.finish();
    }

    private void startWebActivity(int in){
        Bundle bundle = new Bundle();
        bundle.putInt(CALL_KEY,in);
        Intent intent = new Intent(this,DisplayActivity.class);
        intent.putExtra(BUNDLE_KEY,bundle);
        startActivity(intent);
    }
}