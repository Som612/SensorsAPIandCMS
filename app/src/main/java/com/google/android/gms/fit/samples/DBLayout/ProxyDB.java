package com.google.android.gms.fit.samples.DBLayout;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.Date;


/**
 * Created by sheyxpeare on 4/14/16.
 */
public class ProxyDB extends SQLiteOpenHelper {




    public static final String DATABASE_NAME = "pedometer.db";
    public static final String TABLE_NAME_ONE = "profile_table";
    public static final String TABLE_NAME_TWO = "daily_counts_table";
    public static final String Col_1 = "ID";
    public static final String Col_2_ONE = "Username";
    public static final String Col_3_ONE = "Age";
    public static final String Col_4_ONE = "Weight";
    public static final String Col_5_ONE = "Date";
    public static final String Col_6_ONE = "Step_Goal";
    public static final String Col_7_ONE = "Calorie_Goal";
    public static final String Col_2_TWO = "Date";
    public static final String Col_3_TWO = "Step_Count";
    public static final String Col_4_TWO = "Calorie_Count";
    public static final String Col_5_TWO = "Active_Minutes";
    public static final String Col_2_REMOTE = "Username";
    public static final String Col_3_REMOTE = "Password";
    public static final String Col_4_REMOTE = "Friend_1";
    public static final String Col_5_REMOTE = "Friend_2";
    public static final String Col_6_REMOTE = "Friend_3";
    public static final String Col_7_REMOTE = "Friend_4";
    public static final String Col_8_REMOTE = "Friend_5";
    private Context mCtx;

    public ProxyDB(Context context) {
        super(context, DATABASE_NAME, null, 1);
        mCtx = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("PRAGMA foreign_keys = ON");
        db.execSQL("create table " + TABLE_NAME_ONE + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, Username TEXT UNIQUE, Age INTEGER, Weight Integer, Date TEXT, Step_Goal INTEGER, Calorie_Goal INTEGER, FOREIGN KEY(Date) REFERENCES daily_counts_table(Date))");
        db.execSQL("create table " + TABLE_NAME_TWO + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, Date TEXT UNIQUE, Step_Count INTEGER, Calorie_Count INTEGER, Active_Minutes INTEGER)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME_ONE);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME_TWO);
        onCreate(db);

    }

    public boolean insertDataT1(String username, int age, int weight, String date, int stepgoal, int calgoal) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(Col_2_ONE, username);
        cv.put(Col_3_ONE, age);
        cv.put(Col_4_ONE, weight);
        cv.put(Col_5_ONE, date);
        cv.put(Col_6_ONE, stepgoal);
        cv.put(Col_7_ONE, calgoal);
        long result = db.insert(TABLE_NAME_ONE,null ,cv);
        if(result == -1)
            return false;
        else
            return true;
    }

    public boolean insertDataT2(String date, int stepcount, int calcount, int activemins) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(Col_2_TWO, date);
        cv.put(Col_3_TWO, stepcount);
        cv.put(Col_4_TWO, calcount);
        cv.put(Col_5_TWO, activemins);
        long result = db.insert(TABLE_NAME_TWO,null ,cv);
        if(result == -1)
            return false;
        else
            return true;
    }



    public boolean deleteData() {
        return false;
    }

    public boolean updateData() {
        return false;
    }
    public String readData() {
        return null;
    }

    public boolean updateDataT1(String username, int age, int weight, String date, int stepgoal, int calgoal) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(Col_2_ONE, username);
        cv.put(Col_3_ONE, age);
        cv.put(Col_4_ONE, weight);
        cv.put(Col_5_ONE, date);
        cv.put(Col_6_ONE, stepgoal);
        cv.put(Col_7_ONE, calgoal);
        long result = db.update(TABLE_NAME_ONE,cv, "username = ?",new String[] { username });
        if(result == -1)
            return false;
        else
            return true;
    }
}
