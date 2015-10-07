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
import tester.DummyUsers;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Devstream on 05/10/2015.
 */
public class LogViewAdapter extends RecyclerView.Adapter<LogViewAdapter.ItemHolder> {

    private List list;
    private Context context;
    private final String OBJECT_KEY = "OBJECT";
    private final String BUNDLE_KEY = "BUNDLE";



    public LogViewAdapter(List list, Context context) {
        super();
        this.list = list;
        this.context = context;

    }

    private int getLayout(){
        return R.layout.test_card_layout;
    }

    @Override
    public ItemHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(getLayout(),viewGroup,false);
        return new ItemHolder(view);
    }

    @Override
    public void onBindViewHolder(ItemHolder itemHolder, int i) {
        DummyUsers dummy = (DummyUsers)list.get(i);
        itemHolder.name.setText(dummy.getUsername());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ItemHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView name;
        public ItemHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            name = (TextView)itemView.findViewById(R.id.name);
        }

        @Override
        public void onClick(View view) {
            int index = getAdapterPosition();
            callDisplayActivity(index);
        }

        private void callDisplayActivity(int index){
            Intent in = new Intent(context, DisplayActivity.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable(OBJECT_KEY,(Serializable)list.get(index));
            in.putExtra(BUNDLE_KEY, bundle);
            context.startActivity(in);
        }
    }
}
