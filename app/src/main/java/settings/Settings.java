package settings;

import java.io.Serializable;

/**
 * Created by Devstream on 22/10/2015.
 */
public class Settings implements Serializable{

    private String type;
    private int colour = 1;
    private int sound = 3;
    private String notificationType = "phone";
    private int frequency = 1;

    public Settings(String type, int colour, int sound, String notificationType, int frequency){
        this.type = type;
        this.colour = colour;
        this.sound = sound;
        this.notificationType = notificationType;
        this.frequency = frequency;
    }
    
    public Settings(String type){
        this.type = type;
    }

    public int getColour() {
        return colour;
    }

    public void setColour(int colour) {
        this.colour = colour;
    }

    public int getSound() {
        return sound;
    }

    public void setSound(int sound) {
        this.sound = sound;
    }

    public String getNotificationType() {
        return notificationType;
    }

    public void setNotificationType(String notificationType) {
        this.notificationType = notificationType;
    }

    public int getFrequency() {
        return frequency;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }

    public String getType() {
        return type;
    }





}
