package fragments;

import abstracts.AbstractFragment;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import io.buzzerbox.app.R;
import tester.DummyUsers;

import java.io.Serializable;

/**
 * Created by Devstream on 05/10/2015.
 */
public class DetailedViewFragment extends AbstractFragment {


    private static final String KEY = "KEY";
    private DummyUsers dummy;
    private TextView name;
    private TextView password;
    private TextView id;
    private Callback mCallback;

    @Override
    protected int getLayout() {
       return R.layout.test_detailed_view;
    }

    @Override
    protected void instantiateWidgets(View view) {
        name = (TextView)view.findViewById(R.id.name);
        name.setText(dummy.getUsername());

        password = (TextView)view.findViewById(R.id.password);
        password.setText(dummy.getPassword());

        id = (TextView)view.findViewById(R.id.id);
        id.setText(String.valueOf(dummy.getId()));
    }

    public static Fragment newInstance(Object obj){
        Bundle bundle = new Bundle();
        DetailedViewFragment fragment = new DetailedViewFragment();
        bundle.putSerializable(KEY, (Serializable) obj);
        fragment.setArguments(bundle);
        return fragment;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if(!(context instanceof Callback)){
            throw new IllegalStateException();
        }
        mCallback = (Callback) context;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        if(getArguments() != null){
            dummy = (DummyUsers) getArguments().getSerializable(KEY);
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_main, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return mCallback.onOptionsItemSelected(item);
    }

    public interface Callback{
       boolean onOptionsItemSelected(MenuItem item);
    }



}
