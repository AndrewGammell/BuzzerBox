package abstracts;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import interfaces.FragmentControllerInterface;

/**
 * Created by Devstream on 29/09/2015.
 */
public abstract class AbstractListFragment extends ListFragment {

    protected FragmentControllerInterface fragmentControllerInterface;
    protected RecyclerView recyclerView;

    protected abstract int getRecyclerLayout();

    protected abstract int getRecyclerView();

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (!(context instanceof FragmentControllerInterface))
            throw new IllegalStateException();

        fragmentControllerInterface = (FragmentControllerInterface) context;
    }


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
    }

    @Override
    public void onDetach() {
        super.onDetach();

        fragmentControllerInterface = null;
    }
}
