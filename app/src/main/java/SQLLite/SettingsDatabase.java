package SQLLite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.AsyncTask;
import android.util.Log;
import holder.DataHolder;
import settings.Settings;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Devstream on 28/10/2015.
 */
public class SettingsDatabase extends SQLiteOpenHelper {

    public SettingsDatabase(Context context) {
        super(context, SettingsDBSchema.DB_NAME, null, SettingsDBSchema.VERSION);
        Log.d("TAG", "database constructor");
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        Log.d("TAG","database onCreate");
        String CREATE_TABLE = String.format("CREATE TABLE %s(%s,%s,%s,%s,%s)"
                , SettingsDBSchema.TABLE_NAME
                , SettingsDBSchema.DBColumns.TYPE
                , SettingsDBSchema.DBColumns.COLOUR
                , SettingsDBSchema.DBColumns.SOUND
                , SettingsDBSchema.DBColumns.NOTIFICATION_TYPE
                , SettingsDBSchema.DBColumns.FREQUENCY);

        sqLiteDatabase.execSQL(CREATE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String DROP_TABLE = String.format("DROP IF EXISTS %s", SettingsDBSchema.TABLE_NAME);

        sqLiteDatabase.execSQL(DROP_TABLE);
        this.onCreate(sqLiteDatabase);
    }


    public void runBackGroundLoader(){
        new BackGroundLoader().execute();
    }

    public void runBackGroundSaver(){
        new BackGroundSaver().execute();
    }


    private void saveSettings() {
        List<Settings> list = DataHolder.getDataHolder().getSettingsList();

        for (Settings settings : list) {
            boolean added = addEntry(settings);
            Log.d("TAG", "Added to database " + String.valueOf(added));
        }
    }

    public boolean addEntry(Settings settings) {
        SQLiteDatabase sql = this.getReadableDatabase();
        sql.insert(SettingsDBSchema.TABLE_NAME, null, values(settings));
        sql.close();
        return true;
    }

    public void loadSettings() {
        List<Settings> list = new ArrayList<Settings>();
        String query = "SELECT * FROM " + SettingsDBSchema.TABLE_NAME;
        Log.d("TAG", " loading settings Query " + query);
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(query, null);

        Settings settings = null;

        if (cursor.moveToFirst()) {
            do {
                settings = new Settings(
                        cursor.getString(0),
                        cursor.getInt(1),
                        cursor.getInt(2),
                        cursor.getString(3),
                        cursor.getInt(4));
                Log.d("TAG", " count is " + cursor.getCount());
                list.add(settings);
            } while (cursor.moveToNext());
            cursor.close();
        }

        DataHolder.getDataHolder().getSettingsList().addAll(list);
    }

    public boolean deleteFromDatabase(Settings settings) {
        String query = String.format("DROP FROM TABLE %s WHERE type = %s",
                SettingsDBSchema.TABLE_NAME, settings.getType());

        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        sqLiteDatabase.execSQL(query);

        return true;
    }

    public void removeAllFromDataBase(){
        String query = "DELETE FROM " + SettingsDBSchema.TABLE_NAME;
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        sqLiteDatabase.execSQL(query);

        Log.d("TAG", "Emptied table");
    }

    private ContentValues values(Settings settings){
        ContentValues contentValues = new ContentValues();
        contentValues.put(SettingsDBSchema.DBColumns.TYPE, settings.getType());
        contentValues.put(SettingsDBSchema.DBColumns.COLOUR, settings.getColour());
        contentValues.put(SettingsDBSchema.DBColumns.SOUND, settings.getSound());
        contentValues.put(SettingsDBSchema.DBColumns.NOTIFICATION_TYPE, settings.getNotificationType());
        contentValues.put(SettingsDBSchema.DBColumns.FREQUENCY, settings.getFrequency());
        return contentValues;
    }


    private class BackGroundSaver extends AsyncTask<Void,Void,Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Log.d("TAG", "onPreExecute");
            removeAllFromDataBase();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            Log.d("TAG", "doInBackground");
           saveSettings();
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
        }
    }

    private class BackGroundLoader extends AsyncTask<Void,Void,Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
        protected Void doInBackground(Void... voids) {
            Log.d("TAG", "doInBackground");
          loadSettings();
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
        }
    }
}
