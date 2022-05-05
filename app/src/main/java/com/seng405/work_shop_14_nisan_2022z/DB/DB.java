package com.seng405.work_shop_14_nisan_2022z.DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.seng405.work_shop_14_nisan_2022z.Entity.Result;

import java.util.ArrayList;
import java.util.List;

public class DB extends SQLiteOpenHelper {

    private final static String databaseName = "WeatherDB";
    private final static int databaseVersion = 1;
    private String WEATHER_TABLE = "weathers";
    private static DB dbInstance = null;


    private DB(Context context)
    {
        super(context,databaseName,null,databaseVersion);
    }

    public synchronized static DB getInstance(Context context)
    {
        if(dbInstance == null)
        {
            dbInstance = new DB(context.getApplicationContext());
        }

        return dbInstance;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String createQuery = "CREATE TABLE " + WEATHER_TABLE + " ("
                + " ID INTEGER PRIMARY KEY AUTOINCREMENT,"
                + " date TEXT,"
                + " day TEXT,"
                + " icon TEXT,"
                + " description TEXT,"
                + " status TEXT,"
                + " degree TEXT,"
                + " min TEXT,"
                + " max TEXT,"
                + " night TEXT,"
                + " humidity TEXT"
                + " )";

        sqLiteDatabase.execSQL(createQuery);
    }

    public void addNewWeathers(String date,String day,String icon,String description,String status,String degree,String min,String max,String night, String humidity)
    {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put("date",date);
        contentValues.put("day",day);
        contentValues.put("icon",icon);
        contentValues.put("description",description);
        contentValues.put("status",status);
        contentValues.put("degree",degree);
        contentValues.put("min",min);
        contentValues.put("max",max);
        contentValues.put("night",night);
        contentValues.put("humidity",humidity);


        sqLiteDatabase.insert(WEATHER_TABLE,null,contentValues);

        sqLiteDatabase.close();
    }

    public ArrayList<Result> getWeatherList()
    {
        ArrayList<Result> weatherArrayList = new ArrayList<Result>();

        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();

        Cursor cursor = sqLiteDatabase.query(WEATHER_TABLE,null,null,null,null,null,null);

        if(cursor.moveToFirst())
        {
            do {

            }
            while(cursor.moveToNext());
        }
        sqLiteDatabase.close();
        return weatherArrayList;
    }



    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        //sqLiteDatabase.execSQL("ALTER TABLE "+ PERSON_TABLE + " ADD COLUMN age INTEGER");
    }

}
