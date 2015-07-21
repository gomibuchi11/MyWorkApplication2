package jp.android.myapp.myworkapplication;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class MyDbHelper extends SQLiteOpenHelper {

    static final String DATABASE_NAME = "myDatabase.db";
    static final int DATABASE_VERSION = 1;

    static final String TABLE_NAME = "myData";

    static final String ID = "_id";

    static final String ADDRESS = "address";
    static final String STATION = "station_name";
    static final String FARE = "fare";
    static final String TIME = "time";
    static final String STORE = "store";

    public MyDbHelper(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    //uniqueで重複をなくす処理を行う
    public void onCreate(SQLiteDatabase db){
        String query = "create table " + TABLE_NAME + "(" +
                ID + " INTEGER PRIMARY KEY autoincrement," +
                ADDRESS + " TEXT unique," +
                STATION + " TEXT," +
                FARE + " TEXT," +
                TIME + " TEXT," +
                STORE + " TEXT);";
        db.execSQL(query);
    }

    public  void onUpgrade(SQLiteDatabase db,int oldVersion,int newVersion){
        db.execSQL("drop table if exists " + TABLE_NAME);
        onCreate(db);
    }
}
