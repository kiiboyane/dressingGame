package com.e_mobadara.Data;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


public class AudioFileDBHelperClass extends SQLiteOpenHelper {
    //name & version
    private static final String DATABASE_NAME = "audiofiles.db";
    private static final int DATABASE_VERSION = 1;
    public static final String TAG ="AudioFileDBHelperClass";

    public AudioFileDBHelperClass( Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        final String SQL_CREATE_AUDIOFILE_TABLE = "CREATE TABLE " +
                AudioFilesTable.AudioFilesEntry.TABLE_AUDIOFILES + "(" +
                AudioFilesTable.AudioFilesEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                AudioFilesTable.AudioFilesEntry.COLUMN_PATH + " TEXT NOT NULL, " +
                AudioFilesTable.AudioFilesEntry.COLUMN_LANGUE + " TEXT NOT NULL, " +
                AudioFilesTable.AudioFilesEntry.COLUMN_TYPE + " TEXT NOT NULL, " +
                AudioFilesTable.AudioFilesEntry.COLUMN_NAME + " TEXT NOT NULL " +
                ");";

        sqLiteDatabase.execSQL(SQL_CREATE_AUDIOFILE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        Log.w(TAG, "Upgrading database from version " + oldVersion + " to " +
                newVersion + ". OLD DATA WILL BE DESTROYED");
        // Drop the table
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + AudioFilesTable.AudioFilesEntry.TABLE_AUDIOFILES);
        sqLiteDatabase.execSQL("DELETE FROM SQLITE_SEQUENCE WHERE NAME = '" +
                AudioFilesTable.AudioFilesEntry.TABLE_AUDIOFILES + "'");

        // re-create database
        onCreate(sqLiteDatabase);
    }
}
