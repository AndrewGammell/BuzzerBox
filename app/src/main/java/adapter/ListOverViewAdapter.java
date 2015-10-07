package adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import fragments.DetailedViewFragment;
import interfaces.FragmentControllerInterface;
import io.buzzerbox.app.R;
import tester.DummyAlerts;
import tester.DummyUsers;

import java.util.List;

/**
 * Created by Devstream on 05/10/2015.
 */
public class ListOverViewAdapter extends RecyclerView.Adapter<ListOverViewAdapter.ItemHolder> {

    private List list;
    private FragmentControllerInterface control;


    public ListOverViewAdapter(List list,Context context) {
        super();
        this.list = list;
        if(context instanceof FragmentControllerInterface){
            this.control = (FragmentControllerInterface) context;
        }


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
        // DummyUsers dummy = (DummyUsers)list.get(i);
        // itemHolder.name.setText(dummy.getUsername());

        DummyAlerts dummy = (DummyAlerts)list.get(i);
        itemHolder.alarmName.setText(dummy.getAlarmName());
        itemHolder.alarmToday.setText(dummy.getAlarmName());
        itemHolder.alarmYesterday.setText(dummy.getAlarmName());
        itemHolder.alarmTotalWeek.setText(dummy.getAlarmName());
        itemHolder.alarmTotalMonth.setText(dummy.getAlarmName());
        itemHolder.alarmTotal.setText(dummy.getAlarmName());
        itemHolder.alarmTimeSinceLast.setText(dummy.getAlarmName());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ItemHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView alarmName;
        TextView alarmToday;
        TextView alarmYesterday;
        TextView alarmTotalWeek;
        TextView alarmTotalMonth;
        TextView alarmTotal;
        TextView alarmTimeSinceLast;

        TextView ItemHolder(View itemView) {
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

            control.replaceWithFragment(DetailedViewFragment.newInstance(list.get(index)));
        }
    }
}
