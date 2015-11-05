package holder;


import org.joda.time.DateTime;
import singleton.Buzz;
import settings.Settings;
import util.Utility;

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
    private Settings settings;


    public BuzzHolder(String type) {
        this.type = type;
//        this.settings = new Settings(type);
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
        return Utility.timeSinceBuzz(lastBuzz);
    }

    public String getTimeSinceSecondLastBuzz(){
        return Utility.timeSinceBuzz(lastBuzz);
    }


    public void countTimeStamps() {
//    Iterates through the held list buzzes,
//    taking the string time stamp from each buzz and coverts it to a DateTime.
//    passes the DateTime to the updateLastBuzz and isToday/Yesterday/Week/Month methods.


//        resetCounters();
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




    public Settings getSettings(){
        return settings;
    }

    private void resetCounters(){
        today = 0;
        yesterday = 0;
        week = 0;
        month = 0;
        total = 0;
    }


}
