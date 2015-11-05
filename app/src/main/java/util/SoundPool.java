//package util;
//
//import android.content.Context;
//import android.content.res.AssetManager;
//import android.util.Log;
//
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * Created by AK-admin on 05/11/2015.



//  This Class Maybe Useful for getting & passing proper sound into onCLick button_audio_listen //
//  EDITS NEEDED //


// */
//
//public class SoundPool {
//
//    private static final String TAG = "SoundPool";
//    private static final String SOUNDS_FOLDER = "buzzerbox_sounds";
//    private static final int MAX_SOUNDS = 1;
//    private List <Sound> mSounds = new ArrayList<>();
//    private int buzz_audio;
//
//
//    // AUDIO ASSET MANAGER //
//    private AssetManager mAssets;
//
//    // NERDRANCH SOUNDPOOL RE: BEATBOX.JAVA <--- DELETE //
//
//    public SoundPool(Context context){
//
//        mAssets = context.getAssets();
//    // LOAD SOUNDS //
//        mAssets = context.getAssets();
//        loadSounds();
//    }
//
//    private void loadSounds(){
//        String[] buzzAudio;
//        try {
//            buzzAudio = mAssets.list(SOUNDS_FOLDER);
//            Log.i(TAG, "Found " + buzzAudio.length + " sounds");
//        } catch (IOException ioe){
//            Log.e(TAG, "Could not list audio assets", ioe);
//            return;
//        }
//
//        for (String filename : buzzAudio){
//        String assetPath = SOUNDS_FOLDER + "/" + filename;
//            Sound sound = new Sound(assetPath);
//            mSounds.add(sound);
//
//
//        }
//
//    }
//
//}
//
