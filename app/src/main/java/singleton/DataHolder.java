package singleton;

import java.util.List;

/**
 * Created by Devstream on 20/10/2015.
 */
public class DataHolder {
    private static DataHolder dataHolder;
    private List<BuzzBox> boxes;

    private DataHolder() {
    }

    public static DataHolder getDataHolder() {
        if (dataHolder == null) {
            dataHolder = new DataHolder();
        }
        return dataHolder;
    }

    public List<BuzzBox> getListOfBoxes() {
        return boxes;
    }

    public void setListOfBoxes(List<BuzzBox> boxes) {
        this.boxes = boxes;
    }
}
