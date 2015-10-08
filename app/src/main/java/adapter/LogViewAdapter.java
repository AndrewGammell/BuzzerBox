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
public class LogViewAdapter extends RecyclerView.Adapter<LogViewAdapter.ItemHolder> {

    private List list;
//    private ViewController control;


    public LogViewAdapter(List list, Context context) {
        super();
        this.list = list;
//        if(context instanceof ViewController){
//            this.control = (ViewController) context;
//        }


    }

    private int getLayout(){
        return R.layout.logview_card_layout;
    }

    @Override
    public ItemHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(getLayout(),viewGroup,false);
        // Marquee //
        TextView textView = (TextView) view.findViewById(R.id.text_alarm_type);
        textView.setSelected(true); // Set focus to the TextView that will use the marquee //

        return new ItemHolder(view);



    }

    @Override
    public void onBindViewHolder(ItemHolder itemHolder, int i) {
        // DummyUsers dummy = (DummyUsers)list.get(i);
        // itemHolder.name.setText(dummy.getUsername());

        DummyAlerts dummy = (DummyAlerts)list.get(i);
        itemHolder.alarmName.setText(dummy.getAlarmName());
        itemHolder.alarmTimeSinceLast.setText(String.valueOf(dummy.getAlarmTimeSinceLast()));

    }



    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ItemHolder extends RecyclerView.ViewHolder { //implements View.OnClickListener{
        TextView alarmName;
        TextView alarmTimeSinceLast;

        ItemHolder(View itemView) {
            super(itemView);
            //  itemView.setOnClickListener(this);
            alarmName = (TextView)itemView.findViewById(R.id.text_alarm_type);
            alarmTimeSinceLast = (TextView)itemView.findViewById(R.id.text_last_buzz_int_value);
        }

//        @Override
//        public void onClick(View view) {
//            int index = getAdapterPosition();
//
//            control.replaceWithFragment(DetailedViewFragment.newInstance(list.get(index)));
//        }
    }
}
