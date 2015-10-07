package fragments;

import abstracts.AbstractFragment;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import io.buzzerbox.app.R;


/**
 * Created by Devstream on 29/09/2015.
 */
public class OverviewFragment extends AbstractFragment {

    /**
     * create recycler layout and view for displaying.
     */
View.OnClickListener listener;

    public static OverviewFragment newInstance(){
        return new OverviewFragment();
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_overview;
    }

    /**
     *
     * just for testing
     */
    @Override
    protected void instantiateWidgets(View view) {
        listener = (View.OnClickListener) getContext();
                TextView text_placeholder = (TextView) view.findViewById(R.id.txt_placeholder);
                text_placeholder.setOnClickListener(listener);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }
}
