package util;

import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Devstream on 10/11/2015.
 */
public class CustomSoundPlayer {

    public CustomSoundPlayer(Context context){
        loadSound(context);
    }


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
    private List<Integer> soundList = new ArrayList<Integer>();


    public void loadSound(Context context){
        for(int i: Utility.getSounds()){
           soundList.add(sp.load(context,i,loadPriority));
        }
    }

    public void playSound(int sound){
        sp.play(soundList.get(sound),leftVolume,rightVolume,playPriority,loop,rate);
    }




}
