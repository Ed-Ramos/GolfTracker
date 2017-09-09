package com.example.android.golftracker.data;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.android.golftracker.data.GolfContract.GolfEntry;

public class GolfDbHelper extends SQLiteOpenHelper{


    public static final String LOG_TAG = GolfDbHelper.class.getSimpleName();

    /** Name of the database file */
    private static final String DATABASE_NAME = "golf.db";

    /**
     * Database version. If you change the database schema, you must increment the database version.
     */
    private static final int DATABASE_VERSION = 1;

    /**
     * Constructs a new instance of {@link GolfDbHelper}.
     *
     * @param context of the app
     */
    public GolfDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        // Create a String that contains the SQL statement to create the golftracker table
        String SQL_CREATE_GOLFTRACKER_TABLE =  "CREATE TABLE " + GolfEntry.TABLE_NAME + " ("
                + GolfEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + GolfEntry.COLUMN_NAME + " TEXT NOT NULL, "
                + GolfEntry.COLUMN_DATE + " TEXT, "
                + GolfEntry.COLUMN_COURSE + " TEXT NOT NULL, "
                + GolfEntry.COLUMN_SCORE + " INTEGER NOT NULL DEFAULT 75);";

        // Execute the SQL statement
        db.execSQL(SQL_CREATE_GOLFTRACKER_TABLE);

    }//End of OnCreate method


    /**
     * This is called when the database needs to be upgraded.
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // The database is still at version 1, so there's nothing to do be done here.
    }

    public void insertGolf(String name, String date, String course, int score) {

        ContentValues values = new ContentValues();
        values.put(GolfEntry.COLUMN_NAME, name);
        values.put(GolfEntry.COLUMN_DATE, date);
        values.put(GolfEntry.COLUMN_COURSE, course);
        values.put(GolfEntry.COLUMN_SCORE, score);

        SQLiteDatabase db = getWritableDatabase();

        long newRowId = db.insert(GolfEntry.TABLE_NAME,null,values);

        if (newRowId != -1) {
            Log.d(LOG_TAG, "New row entry successful. ID =" + newRowId);
        } else {

            Log.d(LOG_TAG, "New row entry unsuccessful");
        }

    }// end insertGolf


    public Cursor readGolf() {
        SQLiteDatabase db = getReadableDatabase();

        String[] projection = {
                GolfEntry._ID,
                GolfEntry.COLUMN_NAME,
                GolfEntry.COLUMN_DATE,
                GolfEntry.COLUMN_COURSE,
                GolfEntry.COLUMN_SCORE
               };

               Cursor cursor = db.query(GolfEntry.TABLE_NAME, projection, null, null, null,null, null);
        return cursor;
    }


}// End of GolfDbHelper class
