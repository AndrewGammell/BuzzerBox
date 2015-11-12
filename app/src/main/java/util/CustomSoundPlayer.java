package util;

import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;

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

    private final SoundPool   sp = new SoundPool(maxStreams,streamType,srcQuality);
    private final Map<String,Integer>  soundMap = new HashMap<String, Integer>();


    public void loadSound(Context context){
        List<Integer> list = Utility.getSounds();
        for(int i=0; i<list.size();i++){
            int loadPriority = 1;
            soundMap.put("sound"+i,sp.load(context,list.get(i), loadPriority));
        }
    }

    public void playSound(int sound){
        float leftVolume = 1;
        float rightVolume = 1;
        int playPriority = 0;
        int loop = 0;
        float rate = 1;
        sp.play(soundMap.get("sound"+sound), leftVolume, rightVolume, playPriority, loop, rate);
    }




}
