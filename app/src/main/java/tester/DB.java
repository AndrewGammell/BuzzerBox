package tester;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Devstream on 29/09/2015.
 */
public class DB implements Serializable{

    String alarmName;
    int alarmToday;
    int alarmYesterday;
    int alarmTotalWeek;
    int alarmTotalMonth;
    int alarmTotal;
    int alarmTimeSinceLast;


    public DB(String alarmName, int alarmToday, int alarmYesterday, int alarmTotalWeek, int alarmTotalMonth, int alarmTotal, int alarmTimeSinceLast){
        this.alarmName = alarmName;
        this.alarmToday = alarmToday;
        this.alarmYesterday = alarmYesterday;
        this.alarmTotalWeek = alarmTotalWeek;
        this.alarmTotalMonth = alarmTotalMonth;
        this.alarmTotal = alarmTotal;
        this.alarmTimeSinceLast = alarmTimeSinceLast;
    }


    public static List<DB> initialiseDummies(){
        List<DB> list = new ArrayList<DB>();
        // String alarmName & six int fields follow
        list.add(new DB("404 Error", 7, 0, 12, 13, 31, 20));
        list.add(new DB("Account Delete", 0, 23, 4, 31, 62, 20));
        list.add(new DB("New Account", 5, 532, 47, 924 , 3881, 20));
        list.add(new DB("Newsletter Subscription", 0, 6, 0, 88, 62, 20));
        list.add(new DB("Sale App", 7, 0, 12, 13, 31, 20));
        list.add(new DB("Affiliate Link Clicked", 7, 0, 12, 13, 31, 20));
        list.add(new DB("Redirect From Landing Page", 0, 23, 4, 31, 62, 20));
        list.add(new DB("Content Upload", 7, 0, 12, 13, 31, 20));
        list.add(new DB("Content Download", 5, 532, 47, 924 , 3881, 20));
        list.add(new DB("Account Upgrade", 7, 0, 12, 13, 31, 20));
        list.add(new DB("Account Downgrade", 7, 0, 12, 13, 31, 20));
        list.add(new DB("Account Expired", 5, 532, 47, 924 , 3881, 20));
        list.add(new DB("Server Error", 5, 532, 47, 924 , 3881, 20));
        list.add(new DB("Browser Incompatible", 5, 532, 47, 924 , 3881, 20));


        return list;
    }


    public String getAlarmName() {
        return alarmName;
    }

    public int getAlarmToday() {
        return alarmToday;
    }

    public int getAlarmYesterday() {
        return alarmYesterday;
    }

    public int getAlarmTotalWeek() {
        return alarmTotalWeek;
    }

    public int getAlarmTotalMonth() {
        return alarmTotalMonth;
    }

    public int getAlarmTotal() {
        return alarmTotal;
    }

    public int getAlarmTimeSinceLast() {
        return alarmTimeSinceLast;
    }
}
