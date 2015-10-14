package adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import io.buzzerbox.app.R;
import tester.DummyAlerts;

import java.util.List;

/**
 * Created by Devstream on 05/10/2015.
 */
public class OverviewAdapter extends RecyclerView.Adapter<OverviewAdapter.ItemHolder> {

    private List list;


    public OverviewAdapter(List list) {
        super();
        this.list = list;

    }

    private int getLayout(){
        return R.layout.overview_card_layout;
    }

    @Override
    public ItemHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(getLayout(),viewGroup,false);
        return new ItemHolder(view);
    }

    @Override
    public void onBindViewHolder(ItemHolder itemHolder, int i) {
        DummyAlerts dummy = (DummyAlerts)list.get(i);
        itemHolder.alarmName.setText(dummy.getAlarmName());
        itemHolder.alarmToday.setText(String.valueOf(dummy.getAlarmToday()));
        itemHolder.alarmYesterday.setText(String.valueOf(dummy.getAlarmYesterday()));
        itemHolder.alarmTotalWeek.setText(String.valueOf(dummy.getAlarmTotalWeek()));
        itemHolder.alarmTotalMonth.setText(String.valueOf(dummy.getAlarmTotalMonth()));
        itemHolder.alarmTotal.setText(String.valueOf(dummy.getAlarmTotal()));
        itemHolder.alarmTimeSinceLast.setText(String.valueOf(dummy.getAlarmTimeSinceLast()));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ItemHolder extends RecyclerView.ViewHolder {
        TextView alarmName;
        TextView alarmToday;
        TextView alarmYesterday;
        TextView alarmTotalWeek;
        TextView alarmTotalMonth;
        TextView alarmTotal;
        TextView alarmTimeSinceLast;

        ItemHolder(View itemView) {
            super(itemView);
            alarmName = (TextView)itemView.findViewById(R.id.text_alarm_type);
            alarmToday = (TextView)itemView.findViewById(R.id.text_today_int_value);
            alarmYesterday = (TextView)itemView.findViewById(R.id.text_yesterday_int_value);
            alarmTotalWeek = (TextView)itemView.findViewById(R.id.text_total_week_int_value);
            alarmTotalMonth = (TextView)itemView.findViewById(R.id.text_total_month_int_value);
            alarmTotal = (TextView)itemView.findViewById(R.id.text_total_int_value);
            alarmTimeSinceLast = (TextView)itemView.findViewById(R.id.text_last_buzz_int_value);
        }
    }
}