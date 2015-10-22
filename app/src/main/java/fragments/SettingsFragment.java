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
import io.buzzerbox.app.R;
import org.w3c.dom.Text;


public class SettingsFragment extends AbstractFragment {

    private static final String SETTINGS_KEY = "SETTINGS";

    private String buzz_audio_fx;
    private int buzz_custom_colour;
    private Settings settings;

//    private View.OnClickListener activityOnClickListener;
//    private Bundle savedInstanceState;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments() != null){
            settings = getArguments().getSerializable(SETTINGS_KEY);
        }

    }

    @Override
    protected int getLayout() {
        return R.layout.layout_settings;
    }

    public void instantiateWidgets(View view) {

//        buzz_audio_fx = (Spinner) view.findViewById(R.id.spinner_audio_file_list);
//        buzz_custom_colour = (Text) view.findViewById(R.id.);
//
//
//        // CHOOSE AUDIO FILE SETTINGS SPINNER //
//        Spinner spinner = (Spinner) findViewById(R.id.spinner_audio_file_list);
//
//        // Create an Array Adapter using the String Array and a Default Spinner Layout //
//        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
//                this, R.array.spinner_audio_files,
//                android.R.layout.simple_spinner_item);
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


}
