package holder;


import android.util.Log;
import org.joda.time.DateTime;
import singleton.Buzz;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Devstream on 16/10/2015.
 * Holds a list of Buzzes and counts time stamps,
 */
public class BuzzHolder implements Serializable {

    private DateTime todayRef = new DateTime();
    private DateTime yesterdayRef = new DateTime().minusDays(1);

    private String type;
    private int today = 0;
    private int yesterday = 0;
    private int week = 0;
    private int month = 0;
    private int total = 0;
    private DateTime lastBuzz;
    private DateTime secondLastBuzz;
    private List<Buzz> list = new ArrayList<Buzz>();


    public BuzzHolder(String type) {
        this.type = type;
    }

    public List<Buzz> getList() {
        return list;
    }

    public String getType() {
        return type;
    }

    public int getToday() {
        return today;
    }

    public int getYesterday() {
        return yesterday;
    }

    public int getWeek() {
        return week;
    }

    public int getMonth() {
        return month;
    }

    public int getTotal() {
        return total;
    }

    public DateTime getLastBuzz() {
        return lastBuzz;
    }

    public DateTime getSecondLastBuzz() {
        return secondLastBuzz;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTimeSinceLastbuzz(){
        return timeSinceBuzz(lastBuzz);
    }

    public String getTimeSinceSecondLastBuzz(){
        return timeSinceBuzz(lastBuzz);
    }


    void countTimeStamps() {
//    Iterates through the held list buzzes,
//    taking the string time stamp from each buzz and coverts it to a DateTime.
//    passes the DateTime to the updateLastBuzz and isToday/Yesterday/Week/Month methods.
        DateTime dt;
        for (Buzz buzz : list) {
            if(buzz.getUpdated_at() != null ){
                dt = new DateTime(buzz.getUpdated_at());
            }else{
                dt = new DateTime(buzz.getCreated_at());
            }

            updateLastBuzz(dt);

            if (isToday(dt)) {
                today++;
            }else if(isYesterday(dt)) {
                yesterday++;
            }else if(isWeek(dt)) {
                week++;
            }else if(isMonth(dt)) {
                month++;
            }
            total++;
        }
    }


   private boolean isToday(DateTime dateTime) {
//       Takes a DateTime and uses the DateTime todayRef for comparison.
//       if the two dates have the same year/month/day, it returns true for today.

        if (dateTime.getYear() == todayRef.getYear()
                && dateTime.getMonthOfYear() == todayRef.getMonthOfYear()
                && dateTime.getDayOfMonth() == todayRef.getDayOfMonth()) {
            return true;
        }
        return false;
    }

   private boolean isYesterday(DateTime dateTime) {
//       Takes a DateTime and uses the DateTime yesterdayRef for comparison.
//       if the two dates have the same year/month/day, it returns true for yesterday.

        if (dateTime.getYear() == yesterdayRef.getYear()
                && dateTime.getMonthOfYear() == yesterdayRef.getMonthOfYear()
                && dateTime.getDayOfMonth() == yesterdayRef.getDayOfMonth()) {
            return true;
        }
        return false;
    }


   private boolean isWeek(DateTime dateTime) {
//       Takes a DateTime and uses the DateTime todayRef for comparison.
//       checks if the two dates are the same year,
//       then checks if they both have the same value in a week year format.
//       returns true if the week year values match.

        if (dateTime.getYear() == todayRef.getYear()
                && dateTime.getWeekOfWeekyear() == todayRef.getWeekOfWeekyear()) {
            return true;
        }
        return false;
    }


   private boolean isMonth(DateTime dateTime) {
//       Takes a DateTime and uses the DateTime todayRef for comparison.
//       checks if the year and month match.
//       returns true if the values match.

        if (dateTime.getYear() == todayRef.getYear()
                && dateTime.getMonthOfYear() == todayRef.getMonthOfYear()) {
            return true;
        }
        return false;
    }


   private void updateLastBuzz(DateTime newDate){
//       updates the second last buzz to reference the last buzz.
//       updates the last buzz to reference the newest time stamp.

        if(lastBuzz == null){
            lastBuzz = newDate;
        }else if(newDate.isAfter(lastBuzz)){
            secondLastBuzz = lastBuzz;
            lastBuzz = newDate;
        }
    }


    private String timeSinceBuzz(DateTime dateTime){
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

}
