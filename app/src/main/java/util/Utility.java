package util;

import android.content.Context;
import android.util.Log;
import io.buzzerbox.app.R;
import org.joda.time.DateTime;
import singleton.User;
import tester.Database;
import tester.DummyUsers;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Devstream on 29/09/2015.
 */
public class Utility {


    public static boolean isValidUser(Context context){
        User user = User.getInstance(context);
        Database database = new Database(context);

        if(user.getUsername() == null && user.getPassword() == null)
            return false;

        if(database.callingAllDummies().size() == 0){
            for(DummyUsers dummy: DummyUsers.initialiseDummies()){
                database.addEntry(dummy);
            }
        }

        return database.verifyUser(user.getUsername(),user.getPassword());
    }

    public static void logout(Context context){
        User user = User.getInstance(context);
        user.setUsername(null);
        user.setPassword(null);
    }

    public static String timeSinceBuzz(DateTime dateTime){
//        Converts a time stamp and new DateTime to milliseconds,
//        divides the time stamp from the new DateTime,
//        then calculates the leftover milliseconds into larger units of time.

        DateTime now = new DateTime();
        long milliseconds = now.getMillis() - dateTime.getMillis();

        long seconds = (milliseconds / 1000);
        long minutes = (seconds/ 60);
        long hours = (minutes / 60);
        long days = (hours / 24);
        long weeks = (days / 7);
        long months = (weeks / 4);
        long years = (months / 12);

        Log.d("LOG", "seconds = " + seconds);
        Log.d("LOG","minutes = " + minutes);
        Log.d("LOG","hours = " + hours);
        Log.d("LOG","days = " + days);
        Log.d("LOG","weeks = " + weeks);
        Log.d("LOG","months = " + months);
        Log.d("LOG", "years = " + years);


        if(years > 0){
            return years + " years ago";
        }

        if(months > 0){
            return months + " months ago";
        }

        if(weeks > 0){
            return weeks + " weeks ago";
        }

        if(days > 0){
            return days + " days ago";
        }

        if(hours > 0){
            return hours + " hours ago";
        }

        if(minutes > 0){
            return minutes + " minutes ago";
        }

        if(seconds > 0){
            return seconds + " seconds ago";
        }

        return null;
    }

    public static List<Integer> getColours(){
        List<Integer> colours = new ArrayList<Integer>();

        colours.add(R.color.colour_option_1);
        colours.add(R.color.colour_option_2);
        colours.add(R.color.colour_option_3);
        colours.add(R.color.colour_option_4);
        colours.add(R.color.colour_option_5);
        colours.add(R.color.colour_option_6);
        colours.add(R.color.colour_option_7);
        colours.add(R.color.colour_option_8);
        colours.add(R.color.colour_option_9);
        colours.add(R.color.colour_option_10);
        colours.add(R.color.colour_option_11);
        colours.add(R.color.colour_option_12);
        colours.add(R.color.colour_option_13);
        colours.add(R.color.colour_option_14);
        colours.add(R.color.colour_option_15);
        colours.add(R.color.colour_option_16);
        colours.add(R.color.colour_option_17);
        colours.add(R.color.colour_option_18);

        return colours;
    }

}
