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
//    private CheckBox colour;
//    private List<View> colourButtonList;
    private RadioGroup mRadioGroup;
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
        mRadioGroup = (RadioGroup) view.findViewById(R.id.colour_picker_grid);
        mRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){

            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                buttonSwitch(radioGroup.getCheckedRadioButtonId());
                updateDisplay();
            }
        });

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
            Log.d("TAG", "sound in data holder is " + in);
        }

        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {

        }
    }

    private void buttonSwitch(int id){
        switch(id){
            case R.id.colour_1: settings.setColour(0);
                break;
            case R.id.colour_2: settings.setColour(1);
                break;
            case R.id.colour_3: settings.setColour(2);
                break;
            case R.id.colour_4: settings.setColour(3);
                break;
            case R.id.colour_5: settings.setColour(4);
                break;
            case R.id.colour_6: settings.setColour(5);
                break;
            case R.id.colour_7: settings.setColour(6);
                break;
            case R.id.colour_8: settings.setColour(7);
                break;
            case R.id.colour_9: settings.setColour(8);
                break;
            case R.id.colour_10: settings.setColour(9);
                break;
            case R.id.colour_11: settings.setColour(10);
                break;
            case R.id.colour_12: settings.setColour(11);
                break;
            case R.id.colour_13: settings.setColour(12);
                break;
            case R.id.colour_14: settings.setColour(13);
                break;
            case R.id.colour_15: settings.setColour(14);
                break;
            case R.id.colour_16: settings.setColour(15);
                break;
            case R.id.colour_17: settings.setColour(16);
                break;
            case R.id.colour_18: settings.setColour(17);
                break;
        }
        updateDisplay();
    }

    private void setCurrentSettings() {
        spinner.setSelection(settings.getSound());
        updateDisplay();
    }

    private void updateDisplay() {
        background.setBackgroundColor(getResources().getColor(colourList.get(settings.getColour())));
    }


}
