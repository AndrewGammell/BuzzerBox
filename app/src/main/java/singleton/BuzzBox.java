package singleton;

import android.util.Log;
import org.joda.time.DateTime;
import singleton.Buzz;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Devstream on 16/10/2015.
 */
public class BuzzBox implements Serializable {

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


    public BuzzBox(String type) {
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

   private boolean isToday(DateTime date) {
        if (date.getYear() == todayRef.getYear() && date.getMonthOfYear() == todayRef.getMonthOfYear()
                && date.getDayOfMonth() == todayRef.getDayOfMonth()) {
            return true;
        }
        return false;
    }

   private boolean isYesterday(DateTime date) {
        if (date.getYear() == yesterdayRef.getYear() && date.getMonthOfYear() == yesterdayRef.getMonthOfYear()
                && date.getDayOfMonth() == yesterdayRef.getDayOfMonth()) {
            return true;
        }
        return false;
    }

   private boolean isWeek(DateTime date) {
        if (date.getYear() == todayRef.getYear() && date.getWeekOfWeekyear() == todayRef.getWeekOfWeekyear()) {
            return true;
        }
        return false;
    }

   private boolean isMonth(DateTime date) {
        if (date.getYear() == todayRef.getYear() && date.getMonthOfYear() == todayRef.getMonthOfYear()) {
            return true;
        }
        return false;
    }

   private void updateLastBuzz(DateTime newDate){

        if(lastBuzz == null){
            lastBuzz = newDate;
        }else if(newDate.isAfter(lastBuzz)){
            secondLastBuzz = lastBuzz;
            lastBuzz = newDate;
        }
    }

    private String timeSinceBuzz(DateTime timeStamp){
        DateTime now = new DateTime();
        long milsec = now.getMillis() - timeStamp.getMillis();

        long seconds = (milsec / 1000);
        long minutes = (seconds/ 60);
        long hours = (minutes / 60);
        long days = (hours / 24);
        long weeks = (days / 7);
        long months = (weeks / 4);
        long years = (months / 12);

//        Log.d("LOG", "seconds = " + seconds);
//        Log.d("LOG","minutes = " + minutes);
//        Log.d("LOG","hours = " + hours);
//        Log.d("LOG","days = " + days);
//        Log.d("LOG","weeks = " + weeks);
//        Log.d("LOG","months = " + months);
//        Log.d("LOG","years = " + years);


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
