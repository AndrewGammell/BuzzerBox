package abstracts;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Abstract fragment class to be used as the base point of all fragments.
 */

public abstract class AbstractFragment extends Fragment {

    // gets the layout to be inflated from the child fragment
    protected abstract int getLayout();

    // passes the view to child fragments to allow them to create its views.
    protected abstract void instantiateWidgets(View view);


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(getLayout(),container,false);
        instantiateWidgets(view);
        return view;
    }




}
