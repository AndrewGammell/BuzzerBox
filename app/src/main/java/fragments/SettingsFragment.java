package fragments;

import abstracts.AbstractFragment;
import android.content.DialogInterface;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import holder.DataHolder;
import io.buzzerbox.app.R;
import settings.Settings;

import util.MessageTools;
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

    // SOUNDPOOL // AK edit 5-11-2015 //
    // Variables for soundpool //

    private SoundPool soundPool;
    private Button button_spinner_audio_listen;
    private int sound_1, sound_2, sound_3, sound_4, sound_5, sound_6, sound_7, sound_8, sound_9;

    // vars for new soundpool object //
    private final int maxStreams = 3;
    private final int streamType = AudioManager.STREAM_MUSIC;
    private final int srcQuality = 0;

    // Vars for soundpool.load //
    private final int loadPriority = 1;

    // Vars for soundpool.play //
    private final float leftVolume = 1;
    private final float rightVolume = 1;
    private final int playPriority = 0;
    private final int loop = 0;
    private final float rate = 1;

    // << --- SOUNDPOOL //


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            settings = (Settings) getArguments().getSerializable(SETTINGS_KEY);
        }
    }


//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        View view = inflater.inflate((R.layout.layout_settings), container, false);
//
//        loadBuzzAudioFX();
//
//        Button BuzzAudioFX = (Button) view.findViewById(R.id.button_spinner_audio_listen);
//
//        // Change new OnClickListener & implemented methods? below Dialog or viewView ?//
//        // Causing Redline @ Override onClick implemented method below //
//
////        BuzzAudioFX.setOnClickListener(new DialogInterface.OnClickListener() {
////            //
////            @Override
////            public void onClick(DialogInterface dialogInterface, int i) {
////            }
////
////            @Override // Redline Error !! Fix ??
////
////            public void onClick(View view) {
////                // Fix needed here?
////
////            }
////        });
//
//        return view;
//    }
    // Create soundpool and load the files to correct vars //
    // SoundPool is Deprecated but Necessary to include for pre-Lollipop Android Versions //

    private void loadBuzzAudioFX() {
        soundPool = new SoundPool(maxStreams, streamType, srcQuality);
        sound_1 = soundPool.load(getContext(), R.raw.buzz_audio_1, loadPriority);
        sound_2 = soundPool.load(getContext(), R.raw.buzz_audio_2, loadPriority);
        sound_3 = soundPool.load(getContext(), R.raw.buzz_audio_3, loadPriority);
        sound_4 = soundPool.load(getContext(), R.raw.buzz_audio_4, loadPriority);
        sound_5 = soundPool.load(getContext(), R.raw.buzz_audio_5, loadPriority);
        sound_6 = soundPool.load(getContext(), R.raw.buzz_audio_6, loadPriority);
        sound_7 = soundPool.load(getContext(), R.raw.buzz_audio_7, loadPriority);
        sound_8 = soundPool.load(getContext(), R.raw.buzz_audio_8, loadPriority);
        sound_9 = soundPool.load(getContext(), R.raw.buzz_audio_9, loadPriority);
    }

    private class Clicker implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                //  EDITS NEEDED //
                case R.id.button_spinner_audio_listen:
                    soundPool.play(sound_1, leftVolume, rightVolume, playPriority, loop, rate);
                    break;


                // Edit to Pass in Selected Sound Vars here from audio_spinner list when button press is detected //
                // Defaulting to First Sound Only //
                // buzz_audio_1 , 2 3 4 5 6 7 8 9 etc //


//                case R.id.button_spinner_audio_listen: soundPool.play(sound_2, leftVolume, rightVolume, playPriority, loop, rate);
//                    break;
//                case R.id.button_spinner_audio_listen: soundPool.play(sound_3, leftVolume, rightVolume, playPriority, loop, rate);
//                    break;
//                case R.id.button_spinner_audio_listen: soundPool.play(sound_4, leftVolume, rightVolume, playPriority, loop, rate);
//                    break;
//                case R.id.button_spinner_audio_listen: soundPool.play(sound_5, leftVolume, rightVolume, playPriority, loop, rate);
//                    break;
//                case R.id.button_spinner_audio_listen: soundPool.play(sound_6, leftVolume, rightVolume, playPriority, loop, rate);
//                    break;
//                case R.id.button_spinner_audio_listen: soundPool.play(sound_7, leftVolume, rightVolume, playPriority, loop, rate);
//                    break;
//                case R.id.button_spinner_audio_listen: soundPool.play(sound_8, leftVolume, rightVolume, playPriority, loop, rate);
//                    break;
//                case R.id.button_spinner_audio_listen: soundPool.play(sound_9, leftVolume, rightVolume, playPriority, loop, rate);
//                    break;


            }


        }

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

            }
            updateDisplay();
        }


    }
}
