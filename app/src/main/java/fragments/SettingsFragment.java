package fragments;

import abstracts.AbstractFragment;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import holder.DataHolder;
import io.buzzerbox.app.R;
import settings.Settings;

import util.CustomSoundPlayer;
import util.Utility;

import java.util.ArrayList;
import java.util.List;


public class SettingsFragment extends AbstractFragment {

    private static final String SETTINGS_KEY = "SETTINGS";

    private TextView alarmType;
    private Spinner spinnerSound;
    private Spinner spinnerFrequency;
    private Settings settings;
    private ViewGroup background;
    private ViewGroup colourContainer;
    private List<Integer> colourList = Utility.getColours();
    private List<View> checkboxes;
    private CheckBox emailCheckBox;
    private CheckBox phoneCheckBox;
    private CustomSoundPlayer customSoundPlayer = new CustomSoundPlayer();


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            settings = (Settings) getArguments().getSerializable(SETTINGS_KEY);
        }
        customSoundPlayer.loadSound(getContext());
    }


    @Override
    protected int getLayout() {
        return R.layout.layout_settings;
    }

    public void instantiateWidgets(View view) {

        background = (ViewGroup) view.findViewById(R.id.layout_settings);
        colourContainer = (ViewGroup) view.findViewById(R.id.colour_picker_grid);

        alarmType = (TextView) view.findViewById(R.id.text_alarm_type);
        alarmType.setText(settings.getType());

        spinnerSound = (Spinner) view.findViewById(R.id.spinner_audio_file_list);
        spinnerSound.setAdapter(getAdapter(R.array.spinner_audio_files));
        spinnerSound.setOnItemSelectedListener(new SpinnerItemSelectedListener());

        spinnerFrequency = (Spinner) view.findViewById(R.id.spinner_frequency);
        spinnerFrequency.setAdapter(getAdapter(R.array.spinner_frequency));
        spinnerFrequency.setOnItemSelectedListener(new SpinnerItemSelectedListener());

        checkboxes = getViewsByTag(colourContainer, "colour");

        emailCheckBox = (CheckBox)view.findViewById(R.id.notify_by_email);
        emailCheckBox.setOnCheckedChangeListener(new CheckBoxListener());

        phoneCheckBox = (CheckBox)view.findViewById(R.id.notify_by_phone);
        phoneCheckBox.setOnCheckedChangeListener(new CheckBoxListener());

        setupCheckboxes();

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
                       customSoundPlayer.playSound(i);

                    int in = DataHolder.getDataHolder().getSettingsList().get(0).getSound();
                    Log.d("TAG", "sound in data holder is " + in);
                    // Need To add Play Audio from SoundPool Code Here //
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
        if(settings.getNotificationType().equals("phone")){
            phoneCheckBox.setChecked(true);
        }else{
            emailCheckBox.setChecked(true);
        }
        updateDisplay();
    }

    private void updateDisplay() {
        background.setBackgroundColor(getResources().getColor(colourList.get(settings.getColour())));
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

    private void setupCheckboxes() {
        CheckBox c;
        for (View v : checkboxes) {
            c = (CheckBox) v;
            c.setOnCheckedChangeListener(new CheckBoxListener());
        }
    }

    private class CheckBoxListener implements CheckBox.OnCheckedChangeListener {

        @Override
        public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

            switch (compoundButton.getId()) {
                case R.id.colour_1:
                    settings.setColour(0);
                    break;
                case R.id.colour_2:
                    settings.setColour(1);
                    break;
                case R.id.colour_3:
                    settings.setColour(2);
                    break;
                case R.id.colour_4:
                    settings.setColour(3);
                    break;
                case R.id.colour_5:
                    settings.setColour(4);
                    break;
                case R.id.colour_6:
                    settings.setColour(5);
                    break;
                case R.id.colour_7:
                    settings.setColour(6);
                    break;
                case R.id.colour_8:
                    settings.setColour(7);
                    break;
                case R.id.colour_9:
                    settings.setColour(8);
                    break;
                case R.id.colour_10:
                    settings.setColour(9);
                    break;
                case R.id.colour_11:
                    settings.setColour(10);
                    break;
                case R.id.colour_12:
                    settings.setColour(11);
                    break;
                case R.id.colour_13:
                    settings.setColour(12);
                    break;
                case R.id.colour_14:
                    settings.setColour(13);
                    break;
                case R.id.colour_15:
                    settings.setColour(14);
                    break;
                case R.id.colour_16:
                    settings.setColour(15);
                    break;
                case R.id.colour_17:
                    settings.setColour(16);
                    break;
                case R.id.colour_18:
                    settings.setColour(17);
                    break;
                case R.id.notify_by_email:
                    if(b){
                        settings.setNotificationType("email");
                        phoneCheckBox.setChecked(false);
                    }
                    break;
                case R.id.notify_by_phone:
                    if(b){
                        settings.setNotificationType("phone");
                        emailCheckBox.setChecked(false);
                    }
                    break;
            }
            updateDisplay();
        }


    }
}
