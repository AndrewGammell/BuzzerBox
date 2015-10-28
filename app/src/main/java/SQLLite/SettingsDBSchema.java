package SQLLite;

/**
 * Created by Devstream on 28/10/2015.
 */
public class SettingsDBSchema {

    public static final String DB_NAME ="settings_data.db";
    public static final String TABLE_NAME = "settings";
    public static final int VERSION = 1;


    public static class DBColumns{
        public static final String TYPE = "type";
        public static final String COLOUR = "colour";
        public static final String SOUND = "sound";
        public static final String NOTIFICATION_TYPE = "notification_type ";
        public static final String FREQUENCY = "frequency";

    }
}
