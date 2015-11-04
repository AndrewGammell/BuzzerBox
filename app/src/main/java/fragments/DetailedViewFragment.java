package fragments;

import abstracts.AbstractFragment;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import holder.BuzzHolder;
import holder.DataHolder;
import io.buzzerbox.app.R;
import settings.Settings;
import tester.DB;
import util.Utility;

import java.io.Serializable;

/**
 * Created by Devstream on 05/10/2015.
 * Takes in an Object to be displayed
 */
public class DetailedViewFragment extends AbstractFragment {


    private static final String KEY = "KEY";
    private BuzzHolder buzz;
    private Settings settings;
    TextView alarmName;
    TextView alarmToday;
    TextView alarmYesterday;
    ViewGroup viewGroup;
    TextView alarmTotalWeek;
    TextView alarmTotalMonth;
    TextView alarmTotal;
    TextView alarmTimeSinceLast;
//    private Callback mCallback;

    @Override
    protected int getLayout() {
        return R.layout.layout_open_log_detail;
    }


    @Override
    protected void instantiateWidgets(View view) {
        settings = DataHolder.getDataHolder().getSettings(buzz.getType());

        alarmName = (TextView) view.findViewById(R.id.text_alarm_type);
        alarmName.setText(buzz.getType());

        alarmTimeSinceLast = (TextView) view.findViewById(R.id.text_last_buzz_int_value);
        alarmTimeSinceLast.setText(String.valueOf(buzz.getTimeSinceLastbuzz()));

        alarmToday = (TextView) view.findViewById(R.id.text_today_int_value);
        alarmToday.setText(String.valueOf((buzz.getToday())));

        alarmYesterday = (TextView) view.findViewById(R.id.text_yesterday_int_value);
        alarmYesterday.setText(String.valueOf(buzz.getYesterday()));

        alarmTotalWeek = (TextView) view.findViewById(R.id.text_total_week_int_value);
        alarmTotalWeek.setText(String.valueOf(buzz.getWeek()));

        alarmTotalMonth = (TextView) view.findViewById(R.id.text_total_month_int_value);
        alarmTotalMonth.setText(String.valueOf(buzz.getMonth()));

        alarmTotal = (TextView) view.findViewById(R.id.text_total_int_value);
        alarmTotal.setText(String.valueOf(buzz.getTotal()));

        viewGroup = (ViewGroup) view.findViewById(R.id.open_log_title);
        viewGroup.setBackgroundColor(getResources().getColor(Utility.getColours().get(settings.getColour())));
//        mCallback.setCurrentTitle(buzz.getAlarmName());
    }

    public static Fragment newInstance(Object obj) {
        Bundle bundle = new Bundle();
        DetailedViewFragment fragment = new DetailedViewFragment();
        bundle.putSerializable(KEY, (Serializable) obj);
        fragment.setArguments(bundle);
        return fragment;
    }


//    @Override
//    public void onAttach(Context context) {
//        super.onAttach(context);
//
//        if(!(context instanceof Callback)){
//            throw new IllegalStateException();
//        }
//        mCallback = (Callback) context;
//    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setHasOptionsMenu(true); // changed from true to test hiding action bar - AK 13-10-2015 //
        if (getArguments() != null) {
            buzz = (BuzzHolder) getArguments().getSerializable(KEY);
        }
    }

//    @Override
//    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
//        super.onCreateOptionsMenu(menu, inflater);
//        inflater.inflate(R.menu.menu_main, menu);
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        return mCallback.onOptionsItemSelected(item);
//    }
//
//    public interface Callback{
////        boolean onOptionsItemSelected(MenuItem item);
//        void setCurrentTitle(String title);
//    }


}