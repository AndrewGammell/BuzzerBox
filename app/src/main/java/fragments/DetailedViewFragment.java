package fragments;

import abstracts.AbstractFragment;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import io.buzzerbox.app.R;
import android.widget.Spinner;
import tester.DummyAlerts;

import java.io.Serializable;

/**
 * Created by Devstream on 05/10/2015.
 */
public class DetailedViewFragment extends AbstractFragment {


    private static final String KEY = "KEY";
    private DummyAlerts dummy;
    TextView alarmName;
    TextView alarmToday;
    TextView alarmYesterday;
    // TextView alarmTotalWeek;
    // TextView alarmTotalMonth;
    // TextView alarmTotal;
    TextView alarmTimeSinceLast;
    private Callback mCallback;

    @Override
    protected int getLayout() {
        return R.layout.layout_open_log_detail;
    }

    @Override
    protected void instantiateWidgets(View view) {
        alarmName = (TextView)view.findViewById(R.id.text_alarm_type);
        alarmName.setText(dummy.getAlarmName());

        alarmTimeSinceLast = (TextView)view.findViewById(R.id.text_last_buzz_int_value);
        alarmTimeSinceLast.setText(String.valueOf(dummy.getAlarmTimeSinceLast()));

        alarmToday = (TextView)view.findViewById(R.id.text_today_int_value);
        alarmToday.setText(String.valueOf((dummy.getAlarmToday())));

        alarmYesterday = (TextView)view.findViewById(R.id.text_yesterday_int_value);
        alarmYesterday.setText(String.valueOf(dummy.getAlarmYesterday()));

        mCallback.setCurrentTitle(dummy.getAlarmName());

//        // AK 19-10-2015 //
//        // start of Spinner code //
//        // Create an Array Adapter using the string array and a default spinner layout //
//        Spinner spinner = (Spinner) findViewById(R.id.array.spinner_audio_file_list);
//
//        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
//                this, R.array.spinner_audio_file_list, android.R.layout.layout_settings);
//        // end of Spinner code //
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
        setHasOptionsMenu(false); // changed from true to test hiding action bar - AK 13-10-2015 //
        if(getArguments() != null){
            dummy = (DummyAlerts) getArguments().getSerializable(KEY);
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
        void setCurrentTitle(String title);
    }



}