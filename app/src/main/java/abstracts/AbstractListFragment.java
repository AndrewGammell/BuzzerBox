package abstracts;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Devstream on 29/09/2015.
 * <p/>
 * Abstract ListFragment to be the base point of all ListFragments
 */
public abstract class AbstractListFragment extends ListFragment {


    protected RecyclerView recyclerView;

    //    returns the Recycler layout from the child to be inflated
    protected abstract int getRecyclerLayout();

    //    returns the RecyclerView contained in the layout
    protected abstract int getRecyclerView();

    //    returns the adapter needs to populate the views
    protected abstract RecyclerView.Adapter getAdapter();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(getRecyclerLayout(), container, false);
        recyclerView = (RecyclerView) view.findViewById(getRecyclerView());
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(getAdapter());
        return view;

    }

    //  Used to to notify the adapter to update its views in case it has changed
    @Override
    public void onResume() {
        super.onResume();
        recyclerView.getAdapter().notifyDataSetChanged();
    }
}
