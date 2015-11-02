package fragments;

import abstracts.AbstractFragment;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import io.buzzerbox.app.R;
import singleton.User;


/**
 * Created by Devstream on 29/09/2015.
 */
public class LoginFragment extends AbstractFragment {
    private final String EDITTEXT1 = "edittextfield1";
    private final String EDITTEXT2 = "edittextfield2";
    private EditText username;
    private EditText password;
    private Button btn_login;
    private Button btn_forgot_password;
    private Button btn_create_account;
    private View.OnClickListener activityOnClickListener;
    private Bundle mSavedInstanceState;

//  assigns the context to activityOnClickListener and sets mSavedInstanceState.
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("TAG", "onCreate in Login");
        if (getContext() instanceof View.OnClickListener) {
            activityOnClickListener = (View.OnClickListener) getContext();
        }
        this.mSavedInstanceState = savedInstanceState;
    }

//  call a method to populate the text fields.
    @Override
    public void onResume() {
        super.onResume();
        fillTextFields();
    }


    @Override
    protected int getLayout() {
        return R.layout.fragment_login;
    }

//  creates the views and sets they're listeners.
    public void instantiateWidgets(View view) {
        username = (EditText) view.findViewById(R.id.txt_username);
        password = (EditText) view.findViewById(R.id.txt_password);
        btn_login = (Button) view.findViewById(R.id.btn_login);
        btn_login.setOnClickListener(new ClickListener());
        btn_create_account = (Button) view.findViewById(R.id.btn_create_account);
        btn_create_account.setOnClickListener(activityOnClickListener);
        btn_forgot_password = (Button) view.findViewById(R.id.btn_forgot_password);
        btn_forgot_password.setOnClickListener(activityOnClickListener);
    }

//    used to get a new instance of LoginFragment.
    public static Fragment newInstance() {
        return new LoginFragment();
    }

    private class ClickListener implements View.OnClickListener {
//        when the Button is clicked the users password and username are set,
//        so that the instance of user can be call during the authentication process.
        @Override
        public void onClick(View view) {
            Log.d("TAG", "onClick in Login");
            if (username.getText().toString().length() > 0 &&
                    password.getText().toString().length() > 0) {

                User user = User.getInstance(getContext());
                user.setUsername(username.getText().toString());
                user.setPassword(password.getText().toString());

                activityOnClickListener.onClick(view);
            }
        }
    }

    //  saves the contents of the text fields.
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(EDITTEXT1, username.getText().toString());
        outState.putString(EDITTEXT2, password.getText().toString());

    }

    // uses mSavedInstanceState to repopulate the text fields.
    private void fillTextFields() {
        if (mSavedInstanceState != null) {

            if (mSavedInstanceState.getString(EDITTEXT1, null) != null) ;
            username.setText(mSavedInstanceState.getString(EDITTEXT1, null));

            if (mSavedInstanceState.getString(EDITTEXT2, null) != null) ;
            password.setText(mSavedInstanceState.getString(EDITTEXT2, null));
        }
    }

}




