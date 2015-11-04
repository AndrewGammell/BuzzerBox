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

import java.util.List;


public class SettingsFragment extends AbstractFragment {

    private static final String SETTINGS_KEY = "SETTINGS";

    private TextView alarmType;
    private Spinner spinnerSound;
    private Spinner spinnerFrequency;
    private Settings settings;
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

        background = (ViewGroup)view.findViewById(R.id.layout_settings);

        alarmType = (TextView) view.findViewById(R.id.text_alarm_type);
        alarmType.setText(settings.getType());

        spinnerSound = (Spinner) view.findViewById(R.id.spinner_audio_file_list);
        spinnerSound.setAdapter(getAdapter(R.array.spinner_audio_files));
        spinnerSound.setOnItemSelectedListener(new SpinnerItemSelectedListener());

        spinnerFrequency = (Spinner) view.findViewById(R.id.spinner_frequency);
        spinnerFrequency.setAdapter(getAdapter(R.array.spinner_frequency));
        spinnerFrequency.setOnItemSelectedListener(new SpinnerItemSelectedListener());

        setCurrentSettings();
    }

    //    puts a Settings Object into a bundle to be passed into the fragment.
    public static SettingsFragment newInstance(Settings settings) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(SETTINGS_KEY, settings);
        SettingsFragment frag = new SettingsFragment();
        frag.setArguments(bundle);
        return frag;
    }

    private ArrayAdapter<CharSequence> getAdapter(int array) {

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                getContext(), array,
                android.R.layout.simple_spinner_item);

        // Specify the layout to use when the list of choices appears //
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        return adapter;
    }

    private class SpinnerItemSelectedListener implements Spinner.OnItemSelectedListener {

        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            switch (adapterView.getId()) {
                case R.id.spinner_audio_file_list:
                    settings.setSound(i);
                    int in = DataHolder.getDataHolder().getSettingsList().get(0).getSound();
                    Log.d("TAG", "sound in data holder is " + in);
                    break;
                case R.id.spinner_frequency:
                    settings.setFrequency(i + 1);
                    int il = DataHolder.getDataHolder().getSettingsList().get(0).getFrequency();
                    Log.d("TAG", "frequency in data holder is " + il);
            }
        }

        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {

        }
    }



    private void setCurrentSettings() {
        spinnerSound.setSelection(settings.getSound());
        spinnerFrequency.setSelection(settings.getFrequency() - 1);

        updateDisplay();
    }

    private void updateDisplay() {
        background.setBackgroundColor(getResources().getColor(colourList.get(settings.getColour())));
    }






}
