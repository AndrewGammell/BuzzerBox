package adapter;

import activities.DisplayActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
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
 *
 */
public class LogViewAdapter extends RecyclerView.Adapter<LogViewAdapter.ItemHolder> {

    private String BUNDLE_KEY = "BUNDLE";
    private String OBJECT_KEY = "OBJECT";
    private String CALL_KEY = "CALL";
    private List<DummyAlerts> list;
    private Context context;


    public LogViewAdapter(List<DummyAlerts> list, Context context) {
        super();
        this.list = list;
        this.context = context;
    }

    private int getLayout() {
        return R.layout.logview_card_layout;
    }

    @Override
    public ItemHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(getLayout(), viewGroup, false);
        // Marquee //
        TextView textView = (TextView) view.findViewById(R.id.text_alarm_type);
        textView.setSelected(true); // Set focus to the TextView that will use the marquee //

        return new ItemHolder(view);


    }

    @Override
    public void onBindViewHolder(ItemHolder itemHolder, int i) {
        DummyAlerts dummy = (DummyAlerts) list.get(i);
        itemHolder.alarmName.setText(dummy.getAlarmName());
        itemHolder.alarmTimeSinceLast.setText(String.valueOf(dummy.getAlarmTimeSinceLast()));

    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ItemHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView alarmName;
        TextView alarmTimeSinceLast;

        ItemHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            alarmName = (TextView) itemView.findViewById(R.id.text_alarm_type);
            alarmTimeSinceLast = (TextView) itemView.findViewById(R.id.text_last_buzz_int_value);
        }

        /**
         * click to see a detailed Buzz
         * @param view of list item clicked
         */
        @Override
        public void onClick(View view) {
            int index = getAdapterPosition();
            DummyAlerts alert = list.get(index);
            startNewActivity(alert);
        }
    }

    /**
     * start new Activity and pass in the Alert object
     * to view it's details
     * @param alert object is a detailed Buzz
     */
    private void startNewActivity(DummyAlerts alert) {
        Intent intent = new Intent(context, DisplayActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable(OBJECT_KEY, alert);
        bundle.putInt(CALL_KEY,0);
        intent.putExtra(BUNDLE_KEY, bundle);
        context.startActivity(intent);
    }
}
