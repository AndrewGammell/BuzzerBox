package holder;

import singleton.Buzz;
import settings.Settings;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Devstream on 20/10/2015.
 */
public class DataHolder implements Serializable {
    private static DataHolder dataHolder;
    private final List<BuzzHolder> boxes  = new ArrayList<BuzzHolder>();
    private final List<Settings> settingsList = new ArrayList<Settings>();
    private final List<Buzz> buzzList = new ArrayList<Buzz>();


    private DataHolder() {
    }

    public static DataHolder getDataHolder() {
        if (dataHolder == null) {
            dataHolder = new DataHolder();
        }
        return dataHolder;
    }

    public List<BuzzHolder> getListOfBoxes() {
        return boxes;
    }

    public List<Settings> getSettingsList() {

        return settingsList;
    }

    public List<Buzz> getBuzzList() {
        return buzzList;
    }

    public Settings getSettings(String type) {
        for (Settings s : settingsList) {
            if (s.getType().equals(type)) {
                return s;
            }
        }
        return null;
    }

    public BuzzHolder getBuzzHolder(String type){
        for(BuzzHolder holder: boxes){
            if(holder.getType().equals(type)){
                return holder;
            }
        }
        return null;
    }

}
