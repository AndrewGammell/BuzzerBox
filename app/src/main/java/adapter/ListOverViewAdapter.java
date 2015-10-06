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

            control.replaceWithFragment(DetailedViewFragment.newInstance(list.get(index)));
        }
    }
}
