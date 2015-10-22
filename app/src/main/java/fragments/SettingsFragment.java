package fragments;

import abstracts.AbstractFragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.View;
import android.widget.*;
import holder.DataHolder;
import io.buzzerbox.app.R;
import org.w3c.dom.Text;
import util.Settings;


public class SettingsFragment extends AbstractFragment {

    private static final String SETTINGS_KEY = "SETTINGS";

    private TextView alarmType;
    private Spinner spinner;
    private Settings settings;


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

}
