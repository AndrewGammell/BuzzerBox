//package util;
//
//import android.app.Activity;
//import android.os.Bundle;
//import android.view.Menu;
//import android.widget.TextView;
//import io.buzzerbox.app.R;
//
///**
// *  Created by K-Admin on 08/10/2015.
//  EXAMPLE FOR REFERENCE ONLY
// >> ALREADY FUNCTIONING in LogViewAdapter.java & logview_card_layout.xml //

// */
//public class MarqueeText extends Activity{
//
//    @Override
//    public void onCreate(Bundle savedInstancedState){
//
//        super.onCreate(savedInstancedState);
//        setContentView(R.layout.logview_card_layout);
//
//        TextView textView = (TextView) this.findViewById(R.id.text_alarm_type);
//        textView.setSelected(true); // Set focus to the TextView that will use the marquee
//    }
//
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu){
//        getMenuInflater().inflate(R.menu.marqueetext, menu);
//        return true;
//    }
//}
