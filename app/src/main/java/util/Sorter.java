package util;

import android.content.Context;
import android.os.AsyncTask;
import holder.BuzzHolder;
import holder.DataHolder;
import singleton.Buzz;
import singleton.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Devstream on 19/10/2015.
 *
 * AsyncTask that takes the list of buzzes from the user and sorts them into containers for each alert type
 */
public class Sorter extends AsyncTask<Void,Void,List<BuzzHolder>> {
    List<BuzzHolder> buzzHolders = new ArrayList<BuzzHolder>();
    private Context context;

    public Sorter(Context context) {
        super();
        this.context = context;
    }

    @Override
    protected List<BuzzHolder> doInBackground(Void... voids) {
        User user = User.getInstance(context);

        for(Buzz b: user.getBuzzHolder()){
            if(findBoxAndAdd(b)){

            }else{
                makeBox(b);
                findBoxAndAdd(b);
            }
        }
        return buzzHolders;
    }

    @Override
    protected void onPostExecute(List<BuzzHolder> buzzHolders) {
        super.onPostExecute(buzzHolders);
        for(BuzzHolder bb: buzzHolders){
//            Log.d("LOG","containers made "+buzzHolders.size());
            bb.countTimeStamps();

//            String str = String.format("type %s today=%d yesterday=%d week=%d month=%d total=%d last Buzz = ",
//                    bb.getType(),bb.getToday() ,bb.getYesterday(),bb.getWeek(),bb.getMonth(),bb.getTotal());
//            Log.d("LOG", str);
        }
        DataHolder.getDataHolder().setListOfBoxes(buzzHolders);
    }

    private boolean findBoxAndAdd(Buzz buzz){
        for(BuzzHolder c: buzzHolders){
//            Log.d("LOG", "box type "+ c.getType() +" buzz name " + buzz.getName());
            if(c.getType().equals(buzz.getName())){
//                Log.d("LOG","created "+buzz.getCreated_at()+" updated at "+buzz.getUpdated_at());
                c.getList().add(buzz);
                return true;
            }
        }
        return false;
    }

    private void makeBox(Buzz buzz){
        buzzHolders.add(new BuzzHolder(buzz.getName()));
    }
}
