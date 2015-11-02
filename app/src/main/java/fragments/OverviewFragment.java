package fragments;

import abstracts.AbstractListFragment;
import adapter.OverviewAdapter;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import holder.DataHolder;
import io.buzzerbox.app.R;
import tester.DB;


import java.util.List;

/**
 * Created by Devstream on 05/10/2015.
 */
public class OverviewFragment extends AbstractListFragment {

    private Callback mCallback = null;

    @Override
    protected int getRecyclerLayout() {
        return R.layout.layout_recycler;
    }

    @Override
    protected int getRecyclerView() {
        return R.id.recycler_view;
    }

//    assigns the context to mCallback.
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
    }

//    returns the adapter set with the list and context.
    @Override
    protected RecyclerView.Adapter getAdapter() {
        return new OverviewAdapter(getList(),getActivity());
    }

//    return an instance of OverviewFragment.
    public static Fragment newInstance(){
        return new OverviewFragment();
    }

//    return the list to be displayed.
    private List getList(){
        return DataHolder.getDataHolder().getListOfBoxes();
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
