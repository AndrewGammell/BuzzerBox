package adapter;

import activities.DisplayActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import holder.DataHolder;
import io.buzzerbox.app.R;
import singleton.Buzz;
import util.Utility;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Devstream on 05/10/2015.
 */
public class LogViewAdapter extends RecyclerView.Adapter<LogViewAdapter.ItemHolder> {

    private String BUNDLE_KEY = "BUNDLE";
    private String OBJECT_KEY = "OBJECT";
    private String CALL_KEY = "CALL";
    private List list;
    private Context context;
    private List<Integer> colours = Utility.getColours();


    public LogViewAdapter(List list, Context context) {
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
        Buzz buzz = (Buzz) list.get(i);
        itemHolder.alarmColour.setBackgroundColor(context.getResources()
                .getColor(colours.get(DataHolder.getDataHolder().getSettings(buzz.getName()).getColour())));
        itemHolder.alarmName.setText(buzz.getName());
        itemHolder.alarmTimeSinceLast.setText(String.valueOf(buzz.getTimeSinceBuzz()));

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ItemHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView alarmName;
        TextView alarmTimeSinceLast;
        ImageView alarmColour;

        ItemHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            alarmColour = (ImageView) itemView.findViewById(R.id.image_alarm_colour);
            alarmName = (TextView) itemView.findViewById(R.id.text_alarm_type);
            alarmTimeSinceLast = (TextView) itemView.findViewById(R.id.text_last_buzz_int_value);
        }

        @Override
        public void onClick(View view) {
            int index = getAdapterPosition();
            startNewActivity(index);
        }
    }

//    starts a new activity with a CALL_KEY and  Object to bo displayed.
    private void startNewActivity(int position) {
        Buzz buzz = (Buzz)list.get(position);
        Intent intent = new Intent(context, DisplayActivity.class);
        Bundle bundle = new Bundle();
        bundle.putInt(CALL_KEY,0);
        bundle.putSerializable(OBJECT_KEY, DataHolder.getDataHolder().getBuzzHolder(buzz.getName()));
        intent.putExtra(BUNDLE_KEY, bundle);

        context.startActivity(intent);
    }
}