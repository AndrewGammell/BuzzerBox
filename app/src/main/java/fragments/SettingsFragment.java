package fragments;

import abstracts.AbstractFragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import holder.DataHolder;
import io.buzzerbox.app.R;
import org.w3c.dom.Text;
import util.Settings;

import java.util.ArrayList;
import java.util.List;


public class SettingsFragment extends AbstractFragment {

    private static final String SETTINGS_KEY = "SETTINGS";

    private TextView alarmType;
    private Spinner spinner;
    private Settings settings;
    private CheckBox colour;
    private List<View> colourList;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            settings = (Settings) getArguments().getSerializable(SETTINGS_KEY);
        }
    }

    @Override
    protected int getLayout() {
        return R.layout.layout_settings;
    }

    public void instantiateWidgets(View view) {
        colourList = getViewsByTag((ViewGroup)view.findViewById(R.id.colour_picker_grid),"colour");
        setAllOnCheckedChangedListeners();

        alarmType = (TextView) view.findViewById(R.id.text_alarm_type);
        alarmType.setText(settings.getType());

        spinner = (Spinner) view.findViewById(R.id.spinner_audio_file_list);
        spinner.setAdapter(getAdapter());
        spinner.setOnItemSelectedListener(new SpinnerItemSelectedListener());

    }

    public static SettingsFragment newInstance(Settings settings) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(SETTINGS_KEY, settings);
        SettingsFragment frag = new SettingsFragment();
        frag.setArguments(bundle);
        return frag;
    }

    private ArrayAdapter<CharSequence> getAdapter() {

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                getContext(), R.array.spinner_audio_files,
                android.R.layout.simple_spinner_item);

        // Specify the layout to use when the list of choices appears //
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        return adapter;
    }

    private class SpinnerItemSelectedListener implements Spinner.OnItemSelectedListener{

        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            settings.setSound(i);
           int in = DataHolder.getDataHolder().getListOfBoxes().get(0).getSettings().getSound();
            Log.d("S","sound in data holder is "+ in);
        }

        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {

        }
    }

    private class ColourSelectedListener implements CheckBox.OnCheckedChangeListener{



        @Override
        public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

            if(b){
                for(View checkBox:colourList ){
                    colour = (CheckBox) checkBox;
                    if(colour.getId() != compoundButton.getId() && colour.isChecked()){
                        colour.setChecked(false);
                    }
                }
            }

        }
    }

    private void setAllOnCheckedChangedListeners(){
        for(View checkBox: colourList){
            colour = (CheckBox) checkBox;
            colour.setOnCheckedChangeListener(new ColourSelectedListener());
        }
    }

    private ArrayList<View> getViewsByTag(ViewGroup root, String tag){
        ArrayList<View> views = new ArrayList<View>();
        final int childCount = root.getChildCount();
        for (int i = 0; i < childCount; i++) {
            final View child = root.getChildAt(i);
            if (child instanceof ViewGroup) {
                views.addAll(getViewsByTag((ViewGroup) child, tag));
            }

            final Object tagObj = child.getTag();
            if (tagObj != null && tagObj.equals(tag)) {
                views.add(child);
            }

        }
        return views;
    }
}
