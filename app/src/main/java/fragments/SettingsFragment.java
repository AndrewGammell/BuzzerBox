package fragments;

import abstracts.AbstractFragment;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import holder.DataHolder;
import io.buzzerbox.app.R;
import settings.Settings;
import util.Utility;

import java.util.ArrayList;
import java.util.List;


public class SettingsFragment extends AbstractFragment {

    private static final String SETTINGS_KEY = "SETTINGS";

    private TextView alarmType;
    private Spinner spinner;
    private Settings settings;
    private CheckBox colour;
    private List<View> colourButtonList;
    private ViewGroup background;
    private List<Integer> colourList = Utility.getColours();


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
        background = (ViewGroup) view.findViewById(R.id.layout_settings);
        colourButtonList = getViewsByTag((ViewGroup) view.findViewById(R.id.colour_picker_grid), "colour");
        setAllOnCheckedChangedListeners();


        alarmType = (TextView) view.findViewById(R.id.text_alarm_type);
        alarmType.setText(settings.getType());

        spinner = (Spinner) view.findViewById(R.id.spinner_audio_file_list);
        spinner.setAdapter(getAdapter());
        spinner.setOnItemSelectedListener(new SpinnerItemSelectedListener());

        setCurrentSettings();
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

    private class SpinnerItemSelectedListener implements Spinner.OnItemSelectedListener {

        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            settings.setSound(i);
            int in = DataHolder.getDataHolder().getSettingsList().get(0).getSound();
            Log.d("S", "sound in data holder is " + in);
        }

        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {

        }
    }

    private class ColourSelectedListener implements CheckBox.OnCheckedChangeListener {

        @Override
        public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

            if (b) {
                changeSettingsColour(compoundButton.getId());
                for (View checkBox : colourButtonList) {
                    colour = (CheckBox) checkBox;
                    if (colour.getId() != compoundButton.getId() && colour.isChecked()) {
                        colour.setChecked(false);
                    }
                }
            }

        }
    }

    private void setAllOnCheckedChangedListeners() {
        for (View checkBox : colourButtonList) {
            colour = (CheckBox) checkBox;
            colour.setOnCheckedChangeListener(new ColourSelectedListener());
        }
    }

    private ArrayList<View> getViewsByTag(ViewGroup root, String tag) {
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

    private void setCurrentSettings() {
        spinner.setSelection(settings.getSound());
        colour = (CheckBox) colourButtonList.get(settings.getColour());
        colour.setChecked(true);
        updateDisplay();
    }

    private void updateDisplay() {
        background.setBackgroundColor(getResources().getColor(colourList.get(settings.getColour())));
    }

    private void changeSettingsColour(int id) {
        for (int i = 0; i < colourButtonList.size(); i++) {
            if (colourButtonList.get(i).getId() == id) {
                settings.setColour(i);
                updateDisplay();
                int in = DataHolder.getDataHolder().getSettingsList().get(0).getColour();
                Log.d("S", "colour in data holder is " + in);
                break;
            }
        }
    }

}
