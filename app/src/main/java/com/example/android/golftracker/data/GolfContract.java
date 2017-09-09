package com.example.android.golftracker.data;


import android.provider.BaseColumns;

public final class GolfContract {

    private  GolfContract(){}

    public  static abstract class GolfEntry implements BaseColumns {

        public static final String TABLE_NAME = "golftracker";

        public static final String _ID = BaseColumns._ID;
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_DATE = "date";
        public static final String COLUMN_COURSE = "course";
        public static final String COLUMN_SCORE = "score";

    }

    }// End of GolfContract class
