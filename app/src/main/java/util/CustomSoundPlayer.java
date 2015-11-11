package util;

import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Devstream on 10/11/2015.
 */
public class CustomSoundPlayer {

    // vars for new soundpool object //
    private final int maxStreams = 1;
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

    private SoundPool   sp = new SoundPool(maxStreams,streamType,srcQuality);
    private Map<String,Integer>  soundMap = new HashMap<String, Integer>();


    public void loadSound(Context context){
        List<Integer> list = Utility.getSounds();
        for(int i=0; i<list.size();i++){
            soundMap.put("sound"+i,sp.load(context,list.get(i),loadPriority));
        }
    }

    public void playSound(int sound){
        sp.play(soundMap.get("sound"+sound),leftVolume,rightVolume,playPriority,loop,rate);
    }




}
