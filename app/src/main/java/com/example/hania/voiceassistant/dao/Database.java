package com.example.hania.voiceassistant.dao;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class Database {
    private static final String TAG = "VoiceAssistantDatabase";
    private static final String DATABASE_NAME = "voice_assistant_database.db";
    private DatabaseHelper mDbHelper;
    // Increment DB Version on any schema change
    private static final int DATABASE_VERSION = 1;
    private final Context mContext;
    public static RequestDao mRequestDao;
    public static ArgDao mArgDao;



    public Database open() throws SQLException {
        mDbHelper = new DatabaseHelper(mContext);
        SQLiteDatabase mDb = mDbHelper.getWritableDatabase();

        mRequestDao = new RequestDao(mDb);
        mArgDao = new ArgDao(mDb);

        return this;
    }

    public void close() {
        mDbHelper.close();
    }

    public Database(Context context) {
        this.mContext = context;
    }


    private static class DatabaseHelper extends SQLiteOpenHelper {
        DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(IRequestSchema.REQUEST_TABLE_CREATE);
            db.execSQL(IArgsSchema.ARG_TABLE_CREATE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion,
                              int newVersion) {
            Log.w(TAG, "Upgrading database from version "
                    + oldVersion + " to "
                    + newVersion + " which destroys all old data");

            db.execSQL("DROP TABLE IF EXISTS "
                    + IRequestSchema.REQUEST_TABLE);
            db.execSQL("DROP TABLE IF EXISTS "
                    + IArgsSchema.ARGS_TABLE);
            onCreate(db);

        }
    }

}
