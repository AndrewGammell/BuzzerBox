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
    private View.OnClickListener activityOnClickListener;
    private Bundle savedInstanceState;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("TAG", "onCreate in Login");
        if (getContext() instanceof View.OnClickListener) {
            activityOnClickListener = (View.OnClickListener) getContext();
        }
            this.savedInstanceState = savedInstanceState;

    }

    @Override
    public void onResume() {
        super.onResume();
        fillTextFields(savedInstanceState);
    }


    @Override
    protected int getLayout() {
        return R.layout.fragment_login;
    }


    public void instantiateWidgets(View view) {
        username = (EditText) view.findViewById(R.id.txt_username);
        username.setText("andrew");
        password = (EditText) view.findViewById(R.id.txt_password);
        password.setText("alan");
        Button btn_login = (Button) view.findViewById(R.id.btn_login);
        btn_login.setOnClickListener(new ClickListener());
        Button btn_create_account = (Button) view.findViewById(R.id.btn_create_account);
        btn_create_account.setOnClickListener(activityOnClickListener);

    }

    public static Fragment newInstance() {
        return new LoginFragment();
    }

    private class ClickListener implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            Log.d("TAG","onClick in Login");
            if (username.getText().toString().length() > 0 &&
                    password.getText().toString().length() > 0) {

                User user = User.getInstance(getContext());
                user.setUsername(username.getText().toString());
                user.setPassword(password.getText().toString());

                activityOnClickListener.onClick(view);
            }
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(EDITTEXT1,username.getText().toString());
        outState.putString(EDITTEXT2,password.getText().toString());
    }

   private void fillTextFields(Bundle savedInstanceState){
        if(savedInstanceState != null){

           if(savedInstanceState.getString(EDITTEXT1,null) != null);
               username.setText(savedInstanceState.getString(EDITTEXT1,null));

            if(savedInstanceState.getString(EDITTEXT2,null) != null);
                password.setText(savedInstanceState.getString(EDITTEXT2,null));
        }
    }

}




