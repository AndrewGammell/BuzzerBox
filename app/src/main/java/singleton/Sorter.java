package singleton;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import singleton.Buzz;
import singleton.BuzzBox;
import singleton.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Devstream on 19/10/2015.
 *
 * AsyncTask that takes the list of buzzes from the user and sorts them into containers for each alert type
 */
public class Sorter extends AsyncTask<Void,Void,List<BuzzBox>> {
    List<BuzzBox> buzzBoxes = new ArrayList<BuzzBox>();
    private Context context;

    public Sorter(Context context) {
        super();
        this.context = context;
    }

    @Override
    protected List<BuzzBox> doInBackground(Void... voids) {
        User user = User.getInstance(context);

        for(Buzz b: user.getBuzzHolder()){
            if(findBoxAndAdd(b)){

            }else{
                makeBox(b);
                findBoxAndAdd(b);
            }
        }
        return buzzBoxes;
    }

    private boolean findBoxAndAdd(Buzz buzz){
        for(BuzzBox c: buzzBoxes){
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
        buzzBoxes.add(new BuzzBox(buzz.getName()));
    }

    @Override
    protected void onPostExecute(List<BuzzBox> buzzBoxes) {
        super.onPostExecute(buzzBoxes);
        for(BuzzBox bb: buzzBoxes){
//            Log.d("LOG","containers made "+buzzBoxes.size());
            bb.countTimeStamps();

//            String str = String.format("type %s today=%d yesterday=%d week=%d month=%d total=%d last Buzz = ",
//                    bb.getType(),bb.getToday() ,bb.getYesterday(),bb.getWeek(),bb.getMonth(),bb.getTotal());
//            Log.d("LOG", str);
        }
    }

}
