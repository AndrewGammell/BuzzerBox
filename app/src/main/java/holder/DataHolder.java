package holder;

import android.content.Context;
import persistence.DataPersister;
import singleton.Buzz;
import settings.Settings;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Devstream on 20/10/2015.
 */
public class DataHolder implements Serializable{
    private static DataHolder dataHolder;
    private List<BuzzHolder> boxes;
    private List<Settings> settingsList = new ArrayList<Settings>();
    private List<Buzz> buzzList = new ArrayList<Buzz>();


    private DataHolder() {
    }

    public static DataHolder getDataHolder() {
        if (dataHolder == null) {
            dataHolder = new DataHolder();
        }
        return dataHolder;
    }

//    will load a DataHolder if null at runtime, if no DataHolder was saved it will create a new DataHolder
//    public static DataHolder getDataHolder(Context context) {
//        if (dataHolder == null) {
//           if(DataPersister.loadDataHolder(context) != null){
//               dataHolder = DataPersister.loadDataHolder(context)
//           }else{
//               dataHolder = new DataHolder();
//           }
//        }
//        return dataHolder;
//    }

    public List<BuzzHolder> getListOfBoxes() {
        return boxes;
    }

    public void setListOfBoxes(List<BuzzHolder> boxes) {
        this.boxes = boxes;
    }

    public List<Settings> getSettingsList() {
        return settingsList;
    }

    public List<Buzz> getBuzzList() {
        return buzzList;
    }

    public Settings getSettings(String type){
        for(Settings s: settingsList){
            if(s.getType().equals(type)){
                return s;
            }
        }
        return null;
    }

}
