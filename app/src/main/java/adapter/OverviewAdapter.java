package adapter;

import activities.SettingsPageActivity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import holder.BuzzHolder;
import holder.DataHolder;
import io.buzzerbox.app.R;
import util.Utility;

import java.util.List;

/**
 * Created by Devstream on 05/10/2015.
 */
public class OverviewAdapter extends RecyclerView.Adapter<OverviewAdapter.ItemHolder> {

    private List list;
    private final String CALL_POSITION = "CALL_POSITION";
    private Context context;


    public OverviewAdapter(List list, Context context) {
        super();
        this.list = list;
        this.context = context;
    }

    private int getLayout() {
        return R.layout.layout_overview_card;
    }

    @Override
    public ItemHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(getLayout(), viewGroup, false);
        return new ItemHolder(view);
    }

    @Override
    public void onBindViewHolder(ItemHolder itemHolder, int i) {
        BuzzHolder buzzHolder = (BuzzHolder) list.get(i);

       itemHolder.alarmColour.setBackgroundColor(context.getResources().getColor(getSettingsColour(buzzHolder.getType())));
        itemHolder.alarmName.setText(buzzHolder.getType());
        itemHolder.alarmToday.setText(String.valueOf(buzzHolder.getToday()));
        itemHolder.alarmYesterday.setText(String.valueOf(buzzHolder.getYesterday()));
        itemHolder.alarmTotalWeek.setText(String.valueOf(buzzHolder.getWeek()));
        itemHolder.alarmTotalMonth.setText(String.valueOf(buzzHolder.getMonth()));

        itemHolder.alarmTotal.setText(String.valueOf(buzzHolder.getTotal()));
        itemHolder.alarmTimeSinceLast.setText(String.valueOf(buzzHolder.getTimeSinceLastBuzz()));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ItemHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView alarmColour;
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
            alarmColour = (ImageView) itemView.findViewById(R.id.image_alarm_colour);
            alarmName = (TextView) itemView.findViewById(R.id.text_alarm_type);
            alarmToday = (TextView) itemView.findViewById(R.id.text_today_int_value);
            alarmYesterday = (TextView) itemView.findViewById(R.id.text_yesterday_int_value);
            alarmTotalWeek = (TextView) itemView.findViewById(R.id.text_total_week_int_value);
            alarmTotalMonth = (TextView) itemView.findViewById(R.id.text_total_month_int_value);
            alarmTotal = (TextView) itemView.findViewById(R.id.text_total_int_value);
            alarmTimeSinceLast = (TextView) itemView.findViewById(R.id.text_last_buzz_int_value);
        }

        @Override
        public void onClick(View view) {
          goToSettings(getAdapterPosition());
        }

        private void goToSettings(int index) {
            Intent intent = new Intent(context,SettingsPageActivity.class);
            intent.putExtra(CALL_POSITION,index);
            context.startActivity(intent);
        }
    }

//    gets the list of colours and uses the colour index form the settings to find the colour to be displayed.
    private Integer getSettingsColour(String type){
        return  Utility.getColours().get(DataHolder
                .getDataHolder()
                .getSettings(type)
                .getColour());

    }
}