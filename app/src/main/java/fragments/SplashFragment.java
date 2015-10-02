package fragments;

import abstracts.AbstractFragment;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.View;
import io.buzzerbox.app.R;
import util.Utility;

/**
 * Created by Devstream on 29/09/2015.
 */
public class SplashFragment extends AbstractFragment {

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
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.d("TAG", "onCreate of splash");
            countdownToLogin();

    }


    public static Fragment newInstance() {
        return new SplashFragment();
    }

//    private boolean bypassLogin(){
//
//            if(Utility.isValidUser(getContext())){
//                MessageTools.showLongToast(getContext(),"User loaded and validated");
//                return true;
//            }
//
//
//
//        return false;
//    }

    private void countdownToLogin(){

        new CountDownTimer(1000 * 3L, 1000 * 1L) {

            @Override
            public void onTick(long l) {

            }

            @Override
            public void onFinish() {
                if(Utility.isValidUser(getContext())){
                    Log.d("TAG","Validated user in splash");
                    fragmentControllerInterface.replaceWithFragment(OverviewFragment.newInstance());
                }else{
                    Log.d("TAG","Invalid user in splash");
                    fragmentControllerInterface.replaceWithFragment(LoginFragment.newInstance());
                }
            }
        }.start();
    }


}
