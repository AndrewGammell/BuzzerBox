package adapter;

import activities.DetailedViewActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import holder.BuzzHolder;
import holder.DataHolder;
import io.buzzerbox.app.R;
import settings.Settings;
import singleton.Buzz;
import util.Utility;


import java.util.List;

/**
 * Created by Devstream on 05/10/2015.
 */
public class LogViewAdapter extends RecyclerView.Adapter<LogViewAdapter.ItemHolder> {

    private final List<Buzz> list;
    private final Context context;
    private final List<Integer> colours = Utility.getColours();


    public LogViewAdapter(List<Buzz> list, Context context) {
        super();
        this.list = list;
        this.context = context;
    }

    private int getLayout() {
        return R.layout.layout_logview_card;
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
        // DummyUsers dummy = (DummyUsers)list.get(i);
        // itemHolder.name.setText(dummy.getUsername());

            Buzz buzz = list.get(i);
            Settings settings = DataHolder.getDataHolder().getSettings(buzz.getName());

            itemHolder.alarmColour.setBackgroundColor(context.getResources()
                    .getColor(colours.get(DataHolder.getDataHolder().getSettings(buzz.getName()).getColour()))); // This Default hardcoded number as per color picker list  -- may need edit //
            itemHolder.alarmName.setText(buzz.getName());
            itemHolder.alarmTimeSinceLast.setText(String.valueOf(buzz.getTimeSinceBuzz()));


    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ItemHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        final TextView alarmName;
        final TextView alarmTimeSinceLast;
        final ImageView alarmColour;

        ItemHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            alarmColour = (ImageView) itemView.findViewById(R.id.image_alarm_colour);
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
            Buzz alert = list.get(index);
            startNewActivity(DataHolder.getDataHolder().getBuzzHolder(alert.getName()));
        }
    }

    /**
     * start new Activity and pass in the Alert object
     * to view it's details
     *
     */
    private void startNewActivity(BuzzHolder holder) {
        Intent intent = new Intent(context, DetailedViewActivity.class);
        Bundle bundle = new Bundle();
        String OBJECT_KEY = "OBJECT";
        bundle.putSerializable(OBJECT_KEY, holder);
        String CALL_KEY = "CALL";
        bundle.putInt(CALL_KEY,0);
        String BUNDLE_KEY = "BUNDLE";
        intent.putExtra(BUNDLE_KEY, bundle);
        context.startActivity(intent);
    }
}
