package fragments;

import abstracts.AbstractFragment;
import activities.PageViewActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.View;
import interfaces.ViewController;
import io.buzzerbox.app.R;
import util.Utility;

/**
 * Created by Devstream on 29/09/2015.
 */
public class SplashFragment extends AbstractFragment {
    private ViewController viewController;
    /**
     * create splash layout
     */
    @Override
    protected int getLayout() {
        return R.layout.fragment_splash;
    }

    @Override
    protected void instantiateWidgets(View view) {

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if(!(context instanceof ViewController))
            throw new IllegalStateException();

        viewController = (ViewController) context;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.d("TAG", "onCreate of splash");
        countdownToLogin();

    }


    public static Fragment newInstance() {
        return new SplashFragment();
    }

    private void countdownToLogin(){

        new CountDownTimer(1000 * 3L, 1000 * 1L) {

            @Override
            public void onTick(long l) {

            }

            @Override
            public void onFinish() {
                if(Utility.isValidUser(getContext())){
                    Log.d("TAG","Validated user in splash");
                    /**
                     *new intent to page view activity
                     */

                    Intent in = new Intent(getActivity(),PageViewActivity.class);
                    startActivity(in);
                }else{
                    Log.d("TAG","Invalid user in splash");
                    viewController.replaceWithFragment(LoginFragment.newInstance());
                }
            }
        }.start();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        viewController = null;

    }


}