package socialevent.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by user on 2016/9/17.
 */
public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "event.db";
    public static final String TABLE_NAME = "eventlist";
    public static final String TABLE_ID = "tempID";
    public static final String Key_ROW = "ROW";
    public static final String Key_ID = "TEMPID";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "EVENT_NAME";
    public static final String COL_3 = "START_DATE";
    public static final String COL_4 = "END_DATE";
    public static final String COL_5 = "START_TIME";
    public static final String COL_6 = "END_TIME";
    public static final String COL_7 = "VENUE";
    public static final String COL_8 = "LATITUDE";
    public static final String COL_9 = "LONGTITUDE";
    public static final String COL_10 = "NOTES";
    public static final String COL_11 = "CONTACTLIST";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
        SQLiteDatabase db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME +" (ID TEXT PRIMARY KEY,EVENT_NAME TEXT,START_DATE TEXT,END_DATE TEXT,START_TIME TEXT,END_TIME TEXT,VENUE TEXT,LATITUDE DOUBLE,LONGTITUDE DOUBLE,NOTES TEXT,CONTACTLIST TEXT) ");
        db.execSQL("create table " + TABLE_ID + " (ROW INTEGER, TEMPID TEXT) ");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS" + TABLE_NAME);
        onCreate(db);
    }

    public boolean insertData(String id,String eventName,String startDate,String endDate,String startTime,String endTime,String venue,Double latitude,Double longtitude,String notes,String contactList){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1,id);
        contentValues.put(COL_2,eventName);
        contentValues.put(COL_3,startDate);
        contentValues.put(COL_4,endDate);
        contentValues.put(COL_5,startTime);
        contentValues.put(COL_6,endTime);
        contentValues.put(COL_7,venue);
        contentValues.put(COL_8,latitude);
        contentValues.put(COL_9,longtitude);
        contentValues.put(COL_10,notes);
        contentValues.put(COL_11,contactList);
        long result = db.insert(TABLE_NAME,null,contentValues);
        if(result==1)
            return false;
        else
            return true;
    }

    public boolean updateEvent(String id,String eventName,String startDate, String endDate, String startTime,String endTime,String venue, Double latitude, Double longtitude, String notes,String contactList){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2,eventName);
        contentValues.put(COL_3,startDate);
        contentValues.put(COL_4,endDate);
        contentValues.put(COL_5,startTime);
        contentValues.put(COL_6,endTime);
        contentValues.put(COL_7,venue);
        contentValues.put(COL_8,latitude);
        contentValues.put(COL_9,longtitude);
        contentValues.put(COL_10,notes);
        contentValues.put(COL_11,contactList);
        long result = db.update(TABLE_NAME,contentValues,"ID = ?",new String[]{id});
        if(result==1)
            return true;
        else
            return false;
    }

    public Cursor getEvent(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+TABLE_NAME,null);
        return res;
    }

    public Integer deleteEvent(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME,"ID = ?",new String[]{id});
    }
}
