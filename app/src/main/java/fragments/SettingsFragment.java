package fragments;

import abstracts.AbstractFragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
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
        if(getArguments() != null){
            settings =  (Settings) getArguments().getSerializable(SETTINGS_KEY);
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
//        spinner.setAdapter();
//        buzz_audio_fx = (Spinner) view.findViewById(R.id.spinner_audio_file_list);
//        buzz_custom_colour = (Text) view.findViewById(R.id.);
//
//
//        // CHOOSE AUDIO FILE SETTINGS SPINNER //
//        Spinner spinner = (Spinner) findViewById(R.id.spinner_audio_file_list);
//

//
//        // Specify the layout to use when the list of choices appears //
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//
//        // Apply the Adapter to the Spinner //
//        spinner.setAdapter(adapter);
//        //

    }

    public static SettingsFragment newInstance(Settings settings) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(SETTINGS_KEY, settings);
        SettingsFragment frag = new SettingsFragment();
        frag.setArguments(bundle);
        return frag;
    }

//    private class spinnerAdapter extends ArrayAdapter<CharSequence>{
//        //        // Create an Array Adapter using the String Array and a Default Spinner Layout //
////        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
////                this, R.array.spinner_audio_files,
////                android.R.layout.simple_spinner_item);
//    }

}
