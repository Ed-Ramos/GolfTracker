package com.example.android.golftracker;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.example.android.golftracker.data.GolfContract.GolfEntry;
import com.example.android.golftracker.data.GolfDbHelper;

public class MainActivity extends AppCompatActivity {

    private static final String LOG_TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GolfDbHelper mDbHelper = new GolfDbHelper(this);

        mDbHelper.insertGolf("Ed", "Sep 9, 2017", "Firestone CC", 69);
        mDbHelper.insertGolf("Nelson", "Sep 9, 2017", "Firestone CC", 73);
        mDbHelper.insertGolf("Ed", "June 20, 2017", "StonyBrook East", 67);
        mDbHelper.insertGolf("Nelson", "June 20, 2017", "StonyBrook East", 72);

        Cursor cursor = mDbHelper.readGolf();

        TextView displayView = (TextView) findViewById(R.id.text_view_golf_tracker);

        try {


            displayView.append(GolfEntry._ID + " - " +
                    GolfEntry.COLUMN_NAME + " - " +
                    GolfEntry.COLUMN_DATE + " - " +
                    GolfEntry.COLUMN_COURSE + " - " +
                    GolfEntry.COLUMN_SCORE + "\n");

            int idColumnIndex = cursor.getColumnIndex(GolfEntry._ID);
            int nameColumnIndex = cursor.getColumnIndex(GolfEntry.COLUMN_NAME);
            int dateColumnIndex = cursor.getColumnIndex(GolfEntry.COLUMN_DATE);
            int courseColumnIndex = cursor.getColumnIndex(GolfEntry.COLUMN_COURSE);
            int scoreColumnIndex = cursor.getColumnIndex(GolfEntry.COLUMN_SCORE);

            Log.d (LOG_TAG, "ID| NAME| DATE| COURSE| SCORE");

            while (cursor.moveToNext()) {
                int id = cursor.getInt(idColumnIndex);
                String name = cursor.getString(nameColumnIndex);
                String date = cursor.getString(dateColumnIndex);
                String course = cursor.getString(courseColumnIndex);
                int score = cursor.getInt(scoreColumnIndex);

                displayView.append(("\n" + id + " - " +
                        name + " - " +
                        date + " - " +
                        course + " - " +
                        score));

                Log.d(LOG_TAG, id + " " + name + " " + date + " " + course + " " + score);
        }


    } finally {

            cursor.close();
        }

        }
}//End of MainActivity
