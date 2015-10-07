package fragments;

import abstracts.AbstractListFragment;
import adapter.ListOverViewAdapter;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import io.buzzerbox.app.R;
import tester.DummyAlerts;
import util.Utility;

import java.util.List;

/**
 * Created by Devstream on 05/10/2015.
 */
public class ListOverViewFragment extends AbstractListFragment {


    @Override
    protected int getRecyclerLayout() {
        return R.layout.layout_recycler;
    }

    @Override
    protected int getRecyclerView() {
        return R.id.recycler_view;
    }

    @Override
    protected RecyclerView.Adapter getAdapter() {
        return new ListOverViewAdapter(getList(),getActivity());
    }

    public static Fragment newInstance(){
        return new ListOverViewFragment();
    }

    /**
     *
     * @return
     * this list is for test purposes only
     */
    private List getList(){
        return DummyAlerts.initialiseDummies();
    }

}
