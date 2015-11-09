package adapter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import io.buzzerbox.app.R;
import singleton.Buzz;

import java.util.List;

/**
 * Created by Devstream on 05/10/2015.
 *
 */
public class OverviewAdapter extends RecyclerView.Adapter<OverviewAdapter.ItemHolder> {
    private static final String TAG = "OverviewAdapter";
    private List<Buzz> list;

    public OverviewAdapter(List<Buzz> list) {
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
        Buzz dummy = list.get(i);
        itemHolder.alarmName.setText(dummy.getName());
        itemHolder.alarmToday.setText(String.valueOf(dummy.getId()));
        itemHolder.alarmYesterday.setText(String.valueOf(dummy.getUser_id()));
        itemHolder.alarmTotalWeek.setText(dummy.getCreated_at());
        itemHolder.alarmTotalMonth.setText(dummy.getBuzz_key());
        itemHolder.alarmTotal.setText(dummy.getUpdated_at());
        itemHolder.alarmTimeSinceLast.setText(dummy.getIncoming_url());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ItemHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView alarmName;
        TextView alarmToday;
        TextView alarmYesterday;
        TextView alarmTotalWeek;
        TextView alarmTotalMonth;
        TextView alarmTotal;
        TextView alarmTimeSinceLast;

        ItemHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            alarmName = (TextView)itemView.findViewById(R.id.text_alarm_type);
            alarmToday = (TextView)itemView.findViewById(R.id.text_today_int_value);
            alarmYesterday = (TextView)itemView.findViewById(R.id.text_yesterday_int_value);
            alarmTotalWeek = (TextView)itemView.findViewById(R.id.text_total_week_int_value);
            alarmTotalMonth = (TextView)itemView.findViewById(R.id.text_total_month_int_value);
            alarmTotal = (TextView)itemView.findViewById(R.id.text_total_int_value);
            alarmTimeSinceLast = (TextView)itemView.findViewById(R.id.text_last_buzz_int_value);
        }

        @Override
        public void onClick(View view) {
            int index = getAdapterPosition();
            Buzz buzz = list.get(index);
            Log.d(TAG, buzz.toString());
        }
    }
}