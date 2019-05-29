package com.e_mobadara.Database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

@Database(entities = {AudioFile.class}, version = 2)
public abstract class MyDatabase extends RoomDatabase {
    private static final String TAG = "ROOM DATABASE";
    //Log.d(TAG, "create audioFileDao");
    public abstract AudioFileDAO AudioFileDao();
}