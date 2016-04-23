package com.google.android.gms.fit.samples.DBLayout;

import android.database.sqlite.SQLiteDatabase;

/**
 * Created by smukhopadhyay on 4/14/16.
 */
public interface CreateDBInterface {

    public void onCreate(SQLiteDatabase db);
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion);

}
