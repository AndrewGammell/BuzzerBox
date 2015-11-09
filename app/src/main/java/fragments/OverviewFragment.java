package fragments;

import abstracts.AbstractListFragment;
import adapter.OverviewAdapter;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import io.buzzerbox.app.R;
import singleton.Buzz;
import tester.DummyAlerts;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Devstream on 05/10/2015.
 */
public class OverviewFragment extends AbstractListFragment {
    private static final String TAG = "OverviewFragment";
    private static final String KEY = "com.devstream.buzz.KEY";
    private Callback mCallback = null;
    private List<Buzz>buzzList = new ArrayList<Buzz>();

    @Override
    protected int getRecyclerLayout() {
        return R.layout.layout_recycler;
    }

    @Override
    protected int getRecyclerView() {
        return R.id.recycler_view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if(!(context instanceof Callback)){
            throw new IllegalStateException();
        }
        mCallback = (Callback)context;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        Bundle args = getArguments();
        if(args != null)
            buzzList = (List<Buzz>) args.getSerializable(KEY);
        else
            buzzList = getList();
    }

    @Override
    protected RecyclerView.Adapter getAdapter() {
        return new OverviewAdapter(buzzList);
    }

    public static Fragment newInstance(List<Buzz>buzzList){
        Log.d(TAG, "Overview List"+ buzzList.toString());
        Fragment fragment = new OverviewFragment();
        Bundle args = new Bundle();
        args.putSerializable(KEY, (Serializable) buzzList);
        fragment.setArguments(args);
        return fragment;
    }

    /**
     *
     * @return
     * this list is for test purposes only
     */
    private List getList(){
        return DummyAlerts.initialiseDummies();
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
