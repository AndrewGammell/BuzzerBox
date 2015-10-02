package tester;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Devstream on 28/09/2015.
 */
public class Database extends SQLiteOpenHelper {

    public Database(Context context) {
        super(context, DBSchema.DB_NAME, null, DBSchema.VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_TABLE = String.format("CREATE TABLE %s(%s,%s,%s)"
                , DBSchema.TABLE_NAME
                , DBSchema.DBColumns.ID
                , DBSchema.DBColumns.USERNAME
                , DBSchema.DBColumns.PASSWORD);

        sqLiteDatabase.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String DROP_TABLE = String.format("DROP IF EXISTS %s", DBSchema.TABLE_NAME);

        sqLiteDatabase.execSQL(DROP_TABLE);
        this.onCreate(sqLiteDatabase);
    }

    public boolean addEntry(DummyUsers dummy) {
        SQLiteDatabase sql = this.getReadableDatabase();
        sql.insert(DBSchema.TABLE_NAME, null, values(dummy));
        sql.close();
        return true;
    }

    public List<DummyUsers> callingAllDummies() {
        List<DummyUsers> list = new ArrayList<DummyUsers>();
        String query = "SELECT * FROM " + DBSchema.TABLE_NAME;
        Log.d("TAG", " callingAllDummies Query " + query);
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(query, null);

        DummyUsers dummy = null;

        if (cursor.moveToFirst()) {
            do {
                dummy = new DummyUsers(
                        cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2));
                Log.d("TAG", " count is " + cursor.getCount());
                list.add(dummy);
            } while (cursor.moveToNext());
            cursor.close();
        }

        return list;
    }

    public boolean deleteFromDatabase(DummyUsers dummy) {
        String query = String.format("DROP FROM TABLE %s WHERE username = %s",
                DBSchema.TABLE_NAME, dummy.getUsername());

        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        sqLiteDatabase.execSQL(query);

        return true;
    }

    public void removeAllFromDataBase() {
        String query = "DELETE FROM " + DBSchema.TABLE_NAME;
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        sqLiteDatabase.execSQL(query);

        Log.d("TAG", "Emptied table");
    }

    private ContentValues values(DummyUsers dummy) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DBSchema.DBColumns.ID, dummy.getId());
        contentValues.put(DBSchema.DBColumns.USERNAME, dummy.getUsername());
        contentValues.put(DBSchema.DBColumns.PASSWORD, dummy.getPassword());
        return contentValues;
    }

    public boolean verifyUser(String username, String password) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        String query = String.format("SELECT * FROM %s WHERE %s='%s' AND %s='%s'"
                , DBSchema.TABLE_NAME
                , DBSchema.DBColumns.USERNAME
                , username
                , DBSchema.DBColumns.PASSWORD
                , password);

        Cursor cursor = sqLiteDatabase.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            if (cursor.getString(1).equals(username)
                    && cursor.getString(2).equals(password)) {
                return true;
            }
        }
        return false;
    }


}
