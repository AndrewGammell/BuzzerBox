package fragments;

import abstracts.AbstractFragment;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.*;
import android.widget.TextView;
import holder.BuzzHolder;
import holder.DataHolder;
import io.buzzerbox.app.R;
import settings.Settings;
import util.Utility;
import java.io.Serializable;

/**
 * Created by Devstream on 05/10/2015.
 * Takes in an Object to be displayed
 */
public class DetailedViewFragment extends AbstractFragment {


    private static final String KEY = "KEY";
    private BuzzHolder buzz;


    @Override
    protected int getLayout() {
        return R.layout.layout_open_log_detail;
    }


    @Override
    protected void instantiateWidgets(View view) {
        Settings settings = DataHolder.getDataHolder().getSettings(buzz.getType());

        TextView alarmName = (TextView) view.findViewById(R.id.text_alarm_type);
        alarmName.setText(buzz.getType());

        TextView alarmTimeSinceLast = (TextView) view.findViewById(R.id.text_last_buzz_int_value);
        alarmTimeSinceLast.setText(String.valueOf(buzz.getTimeSinceLastbuzz()));

        TextView alarmToday = (TextView) view.findViewById(R.id.text_today_int_value);
        alarmToday.setText(String.valueOf((buzz.getToday())));

        TextView alarmYesterday = (TextView) view.findViewById(R.id.text_yesterday_int_value);
        alarmYesterday.setText(String.valueOf(buzz.getYesterday()));

        TextView alarmTotalWeek = (TextView) view.findViewById(R.id.text_total_week_int_value);
        alarmTotalWeek.setText(String.valueOf(buzz.getWeek()));

        TextView alarmTotalMonth = (TextView) view.findViewById(R.id.text_total_month_int_value);
        alarmTotalMonth.setText(String.valueOf(buzz.getMonth()));

        TextView alarmTotal = (TextView) view.findViewById(R.id.text_total_int_value);
        alarmTotal.setText(String.valueOf(buzz.getTotal()));

        ViewGroup viewGroup = (ViewGroup) view.findViewById(R.id.open_log_title);
        viewGroup.setBackgroundColor(getResources().getColor(Utility.getColours().get(settings.getColour())));

    }

    public static Fragment newInstance(Object obj) {
        Bundle bundle = new Bundle();
        DetailedViewFragment fragment = new DetailedViewFragment();
        bundle.putSerializable(KEY, (Serializable) obj);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null)
            buzz = (BuzzHolder) getArguments().getSerializable(KEY);
        }

}