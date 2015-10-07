package abstracts;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * Created by Devstream on 29/09/2015.
 */
public abstract class AbstractFragment extends Fragment {

    protected abstract int getLayout();
    protected abstract void instantiateWidgets(View view);






    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(getLayout(),container,false);
        instantiateWidgets(view);
        return view;
    }




}
