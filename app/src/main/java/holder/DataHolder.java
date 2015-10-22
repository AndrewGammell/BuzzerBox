package holder;

import util.Settings;

import java.util.List;

/**
 * Created by Devstream on 20/10/2015.
 */
public class DataHolder {
    private static DataHolder dataHolder;
    private List<BuzzHolder> boxes;

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

    public void setListOfBoxes(List<BuzzHolder> boxes) {
        this.boxes = boxes;
    }

}
