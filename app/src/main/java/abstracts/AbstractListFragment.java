package abstracts;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.*;
import io.buzzerbox.app.R;
import util.Utility;

/**
 * Created by Devstream on 29/09/2015.
 */
public abstract class AbstractListFragment extends ListFragment {


    protected RecyclerView recyclerView;


    protected abstract int getRecyclerLayout();

    protected abstract int getRecyclerView();

    protected abstract RecyclerView.Adapter getAdapter();




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(getRecyclerLayout(), container, false);
        recyclerView = (RecyclerView) view.findViewById(getRecyclerView());
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        return view;

    }


    /**
     *
     * add custom adapter here and set to recycler.
     */
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        recyclerView.setAdapter(getAdapter());
    }


}
