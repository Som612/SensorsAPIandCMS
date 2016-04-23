package com.google.android.gms.fit.samples.DBLayout;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


/**
 * Created by smukhopadhyay on 4/14/16.
 */
public class ProxyDB extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "pedometer.db";
    public static final String TABLE_NAME_ONE = "profile_table";
    public static final String TABLE_NAME_TWO = "daily_counts_table";
    public static final String TABLE_NAME_REMOTE = "friends_table";
    public static final String Col_1 = "ID";
    public static final String Col_2_ONE = "Username";
    public static final String Col_3_ONE = "Age";
    public static final String Col_4_ONE = "Weight";
    public static final String Col_5_ONE = "Date";
    public static final String Col_6_ONE = "Step_Goal";
    public static final String Col_7_ONE = "Calorie_Goal";
    public static final String Col_8_ONE = "Has_Profile_Pic";
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
        db.execSQL("create table " + TABLE_NAME_ONE + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, Username TEXT UNIQUE, Age INTEGER, Weight Integer, Date TEXT, Step_Goal INTEGER, Calorie_Goal INTEGER, Has_Profile_Pic TEXT, FOREIGN KEY(Date) REFERENCES daily_counts_table(Date))");
        db.execSQL("create table " + TABLE_NAME_TWO + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, Date TEXT, Step_Count INTEGER, Calorie_Count INTEGER, Active_Minutes INTEGER)");
        db.execSQL("create table " + TABLE_NAME_REMOTE + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, Username TEXT UNIQUE, Password TEXT, Friend_1 TEXT, Friend_2 TEXT, Friend_3 TEXT, Friend_4 TEXT, Friend_5 TEXT, FOREIGN KEY(Username) REFERENCES profile_table(Username))");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME_ONE);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME_REMOTE);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME_TWO);
        onCreate(db);

    }

    public boolean deleteData() {
        return false;
    }

    public String readData() {
        return null;
    }

    public boolean updateData() {
        return false;
    }
}
